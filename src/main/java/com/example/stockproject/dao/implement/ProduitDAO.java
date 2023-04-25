package com.example.stockproject.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Produit;

public class ProduitDAO extends DAO<Produit> {
	public ProduitDAO(Connection conn) {
		super(conn);
	}

	/**
	 * Créer un nouveau produit à partir d'un objet Product
	 * 
	 */
	@Override
	public boolean create(Produit obj) {
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO produit (nom,stock,isActive) VALUES (?,?,?)");
			ps.setString(1, obj.get_nom());
			ps.setDouble(2, obj.get_stock());
			ps.setBoolean(3, obj.get_isActive());
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Produit obj) {
		try {
			if (checkForProduits(obj)) {
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement("UPDATE produit SET isActive = ? WHERE id_produit =?");
				ps.setBoolean(1, false);
				ps.setInt(2, obj.get_idproduit());
				ps.executeUpdate();
				ps.close();
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			} else {
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement("DELETE FROM produit WHERE id_produit =?");
				ps.setInt(1, obj.get_idproduit());
				ps.executeUpdate();
				ps.close();
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Produit obj) {
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn
					.prepareStatement("UPDATE produit SET nom =?, stock =?, isActive=? WHERE id_produit =?");
			ps.setString(1, obj.get_nom());
			ps.setInt(2, obj.get_stock());
			ps.setBoolean(3, obj.get_isActive());
			ps.setInt(4, obj.get_idproduit());
			ps.executeUpdate();
			ps.close();
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Produit find(int id) {
		Produit produit = null;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit WHERE id_produit =?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				produit = new Produit(rs.getInt("id_produit"), rs.getString("nom"), rs.getInt("stock"),
						rs.getBoolean("isActive"));
			}
			conn.commit();
			conn.setAutoCommit(true);
			return produit;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Produit> findall() {
		List<Produit> produits;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit WHERE isActive = 1");
			ResultSet rs = ps.executeQuery();
			produits = new ArrayList<>();
			while (rs.next()) {
				produits.add(new Produit(rs.getInt("id_produit"), rs.getString("nom"), rs.getInt("stock"),
						rs.getBoolean("isActive")));
			}
			conn.commit();
			conn.setAutoCommit(true);
			return produits;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Produit> findallinactive() {
		List<Produit> produits;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit WHERE isActive = 0");
			ResultSet rs = ps.executeQuery();
			produits = new ArrayList<>();
			while (rs.next()) {
				produits.add(new Produit(rs.getInt("id_produit"), rs.getString("nom"), rs.getInt("stock"),
						rs.getBoolean("isActive")));
			}
			conn.commit();
			conn.setAutoCommit(true);
			return produits;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkForProduits(Produit obj) {
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit_facture WHERE id_produit=?");
			ps.setInt(1, obj.get_idproduit());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			conn.commit();
			conn.setAutoCommit(true);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
