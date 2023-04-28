package com.example.stockproject.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Facture;
import com.example.stockproject.models.ProduitQuantite;

public class FactureDAO extends DAO<Facture> {

	private UtilisateurDAO utilisateurDAO;
	private ProduitDAO produitDAO;
	private ClientDAO clientDAO;

	public FactureDAO(Connection conn) {
		super(conn);
		this.utilisateurDAO = new UtilisateurDAO(conn);
		this.produitDAO = new ProduitDAO(conn);
		this.clientDAO = new ClientDAO(conn);
	}

	/**
	 * Créer une facture dans la table facture tout en mettant à jour la table
	 * produit_facture La création nécessite de faire une liste et d'insérer les
	 * éléments de produit_facture au travers d'une boucle
	 * 
	 * @param obj : objet à créer dans la BDD
	 * @return
	 */
	@Override
	public boolean create(Facture obj) {
		try {
			if (!obj.get_produitQuantite().isEmpty()) {
				conn.setAutoCommit(false);
				// Statement.RETURN_GENERATED_KEYS --> Récupère toutes les informations générés
				// par l'insertion dans notre base de données. On peut réutilisé ces données
				// avec
				// getGeneratedKeys qui contient toutes les colonnes de la ligne générés qui
				// dans notre cas contient l'id de la facture pour facture produit
				PreparedStatement ps = conn.prepareStatement(
						"INSERT INTO `facture`(`id_client`,`id_utilisateur`) VALUES (?,?);",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, obj.get_client().get_idClient());
				ps.setInt(2, obj.get_utilisateur().get_idUtilisateur());
				ps.executeUpdate();
				ResultSet genkeys = ps.getGeneratedKeys();
				if (genkeys.next()) {
					int id_facture = genkeys.getInt(1);
					obj.get_produitQuantite().forEach(entry -> {
						try {
							// Check de la quantite de stock avant d'appliquer celle-ci à la base de données
							if (this.produitDAO.find(entry.getProduit().get_idProduit()).get_stock() > entry
									.getQuantite()) {
								PreparedStatement ps2 = conn.prepareStatement(
										"INSERT INTO produit_facture(id_facture,id_produit,quant) VALUES (?,?,?);");
								ps2.setInt(1, id_facture);
								ps2.setInt(2, entry.getProduit().get_idProduit());
								ps2.setInt(3, entry.getQuantite());
								ps2.executeUpdate();
								ps2.close();
								PreparedStatement ps3 = conn
										.prepareStatement("UPDATE produit SET stock = stock - ? WHERE id_produit =?;");
								ps3.setInt(1, entry.getQuantite());
								ps3.setInt(2, entry.getProduit().get_idProduit());
								ps3.executeUpdate();
								ps3.close();
							} else {
								throw new Exception(
										"Pas assez de stock de l'article" + entry.getProduit().get_idProduit());
							}
						} catch (Exception e) {
							e.printStackTrace();
							return;
						}
					});
				}
				genkeys.close();
				ps.close();
				conn.commit();
				conn.setAutoCommit(true);
			} else {
				PreparedStatement ps = conn
						.prepareStatement("INSERT INTO `facture`(`id_client`,`id_utilisateur`) VALUES (?,?);");
				ps.setInt(1, obj.get_client().get_idClient());
				ps.setInt(2, obj.get_utilisateur().get_idUtilisateur());
				ps.executeUpdate();
				ps.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Supprime une facture ainsi que ses entrées dans produit_facture au travers de
	 * 2 requête sql delete et avec un ensemble de requête qui rajoute les éléments
	 * au stock à noter que vérifier si une facture impacte les autres tables n'est
	 * pas nécessaire car les factures sont indépendantes des autres tables.
	 * 
	 * @param obj : Objet à supprimer dans la BDD
	 * @return
	 */
	@Override
	public boolean delete(Facture obj) {
		try {
			if (!obj.get_produitQuantite().isEmpty()) {
				for (ProduitQuantite i : obj.get_produitQuantite()) {
					this.produitDAO.addback(i.getProduit(), i.getQuantite());
				}
			}
			conn.setAutoCommit(false);
			PreparedStatement psdfp = conn.prepareStatement("DELETE FROM produit_facture WHERE id_facture = ?");
			psdfp.setInt(1, obj.get_idFacture());
			psdfp.executeUpdate();
			psdfp.close();
			PreparedStatement psdf = conn.prepareStatement("DELETE FROM facture WHERE id_facture = ?");
			psdf.setInt(1, obj.get_idFacture());
			psdf.executeUpdate();
			psdf.close();
			conn.setAutoCommit(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Modifie les informations de la facture ainsi que les éléments de table
	 * produit_facture
	 * 
	 * @param obj : Objet à modifier dans la BDD
	 * @return
	 */
	@Override
	public boolean update(Facture obj) {
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn
					.prepareStatement("UPDATE `facture` SET `id_client`=?,`id_utilisateur`=? WHERE `id_facture` =?;");
			ps.setInt(1, obj.get_client().get_idClient());
			ps.setInt(2, obj.get_utilisateur().get_idUtilisateur());
			ps.setInt(3, obj.get_idFacture());
			ps.executeUpdate();
			obj.get_produitQuantite().forEach(entry -> {
				try {
					PreparedStatement ps2 = conn.prepareStatement(
							"UPDATE produit_facture SET id_facture = ? ,id_produit =? ,quant =? WHERE id_facture=?;");
					ps2.setInt(1, obj.get_idFacture());
					ps2.setInt(2, entry.getProduit().get_idProduit());
					ps2.setInt(3, entry.getQuantite());
					ps2.setInt(4, obj.get_idFacture());
					ps2.executeUpdate();
					ps2.close();
					// soustrait la quantité de la table produit_facture au stock.
					PreparedStatement ps3 = conn
							.prepareStatement("UPDATE produit SET stock = stock - ? WHERE id_produit =?;");
					ps3.setInt(1, entry.getQuantite());
					ps3.setInt(2, entry.getProduit().get_idProduit());
					ps3.executeUpdate();
					ps3.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			});
			ps.close();
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * créé une facture à partir de l'id de la facture
	 * 
	 * @param idFacture : index de l'élément à chercher
	 * @return
	 */
	@Override
	public Facture find(int idFacture) {
		try {
			Facture facture = null;
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM `facture` as f LEFT JOIN produit_facture as pf ON f.id_facture=pf.id_facture WHERE f.id_facture=?; ");
			ps.setInt(1, idFacture);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (facture == null) {
					facture = new Facture(rs.getInt("f.id_facture"), this.clientDAO.find(rs.getInt("f.id_client")),
							this.utilisateurDAO.find(rs.getInt("f.id_utilisateur")), new ArrayList<>());
				}
				facture.get_produitQuantite().add(
						new ProduitQuantite(this.produitDAO.find(rs.getInt("pf.id_produit")), rs.getInt("pf.quant")));
			}
			conn.setAutoCommit(false);
			ps.close();
			conn.commit();
			conn.setAutoCommit(true);
			return facture;
		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * retourne la liste de toutes les factures avec les produits vendus par dans
	 * chaque factures
	 */
	@Override
	public List<Facture> findall() {
		try {
			Facture facture = new Facture();
			ArrayList<ProduitQuantite> tempProduct = new ArrayList<>();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM `facture` as f LEFT JOIN produit_facture as pf ON f.id_facture=pf.id_facture; ");
			ResultSet rs = ps.executeQuery();
			List<Facture> factures = new java.util.ArrayList<>();
			while (rs.next()) {
				Integer idtemp = rs.getInt("f.id_facture");
				if (idtemp != facture.get_idFacture()) {
					if (!tempProduct.isEmpty()) {
						facture.set_produitQuantite(tempProduct);
						factures.add(facture);
					}
					tempProduct = new ArrayList<>();
					facture = new Facture(rs.getInt("f.id_facture"), this.clientDAO.find(rs.getInt("f.id_client")),
							this.utilisateurDAO.find(rs.getInt("f.id_utilisateur")), new ArrayList<>());
				}
				if (rs.getInt("f.id_facture") == facture.get_idFacture()) {
					tempProduct.add(new ProduitQuantite(this.produitDAO.find(rs.getInt("pf.id_produit")),
							rs.getInt("pf.quant")));
				}
			}
			conn.setAutoCommit(false);
			if (facture.get_idFacture() != 0) {
				facture.set_produitQuantite(tempProduct);
				factures.add(facture);
			}
			ps.close();
			conn.commit();
			conn.setAutoCommit(true);
			return factures;
		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}