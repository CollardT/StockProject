package com.example.stockproject.dao.implement;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Facture;
import com.example.stockproject.models.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public boolean create(Facture obj) {
        try {
            if(!obj.get_produitsvendus().isEmpty()){
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO `facture`(`id_client`,`id_utilisateur`) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, obj.get_Client().get_idClient());
                ps.setInt(2, obj.get_Utilisateur().get_idUtilisateur());
                ps.executeUpdate();
                ResultSet genkeys = ps.getGeneratedKeys();
                if (genkeys.next()) {
                   int id_facture = genkeys.getInt(1);
                    for (Map.Entry<Produit, Integer> entry : obj.get_produitsvendus().entrySet())
                    {
                        PreparedStatement ps2 = conn.prepareStatement("INSERT INTO produit_facture(id_facture,id_produit,quant) VALUES (?,?,?);");
                        ps2.setInt(1, id_facture);
                        ps2.setInt(2, entry.getKey().get_idProduit());
                        ps2.setInt(3, entry.getValue());
                        ps2.executeUpdate();
                        ps2.close();
                        PreparedStatement ps3 = conn.prepareStatement("UPDATE produit SET stock = stock - ? WHERE id_produit =?;");
                        ps3.setInt(1, entry.getValue());
                        ps3.setInt(2, entry.getKey().get_idProduit());
                        ps3.executeUpdate();
                    }
                }
                genkeys.close();
                ps.close();
                conn.commit();
                conn.setAutoCommit(true);
            }
            else {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO `facture`(`id_client`,`id_utilisateur`) VALUES (?,?);");
                ps.setInt(1, obj.get_Client().get_idClient());
                ps.setInt(2, obj.get_Utilisateur().get_idUtilisateur());
                ps.executeUpdate();
                ps.close();
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Facture obj) {
        return false;
    }

    @Override
    public boolean update(Facture obj) {
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("UPDATE `facture` SET `id_client`=?,`id_utilisateur`=? WHERE `id_facture` =?;");
            ps.setInt(1, obj.get_Client().get_idClient());
            ps.setInt(2, obj.get_Utilisateur().get_idUtilisateur());
            ps.setInt(3, obj.get_idFacture());
            ps.executeUpdate();
            for ( Map.Entry<Produit, Integer> entry : obj.get_produitsvendus().entrySet()
                 ) {
                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO produit_facture(id_facture,id_produit,quant) VALUES (?,?,?);");
                ps2.setInt(1, obj.get_idFacture());
                ps2.setInt(2, entry.getKey().get_idProduit());
                ps2.setInt(3, entry.getValue());
                ps2.executeUpdate();
                ps2.close();
                PreparedStatement ps3 = conn.prepareStatement("UPDATE produit SET stock = stock -? WHERE id_produit =?;");
                ps3.setInt(1, entry.getValue());
                ps3.setInt(2, entry.getKey().get_idProduit());
                ps3.executeUpdate();
                ps3.close();

            }
            ps.close();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Facture find(int idFacture) {
        try {
            Facture facture = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `facture` as f LEFT JOIN produit_facture as pf ON f.id_facture=pf.id_facture WHERE f.id_facture=?; ");
            ps.setInt(1, idFacture);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(facture == null){
                    facture = new Facture(
                    rs.getInt("f.id_facture"),
                    this.clientDAO.find(rs.getInt("f.id_client")),
                    this.utilisateurDAO.find(rs.getInt("f.id_utilisateur")),
                    new HashMap<>()
                    );
                }
                facture.get_produitsvendus().put(this.produitDAO.find(rs.getInt("pf.id_produit")),rs.getInt("pf.quant"));
            }
            ps.close();
            return facture;
        }


        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Facture> findall() {
        try {
            Facture facture = new Facture();
            HashMap<Produit,Integer> tempProduct = new HashMap<>();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `facture` as f LEFT JOIN produit_facture as pf ON f.id_facture=pf.id_facture; ");
            ResultSet rs = ps.executeQuery();
            List<Facture> factures = new java.util.ArrayList<>();
            while (rs.next()) {
                Integer idtemp = rs.getInt("f.id_facture");
                if (idtemp != facture.get_idFacture()) {
                        if(!tempProduct.isEmpty()){
                            facture.set_produitsvendus(tempProduct);
                            factures.add(facture);
                        }
                        tempProduct = new HashMap<>();
                        facture = new Facture(
                                rs.getInt("f.id_facture"),
                                this.clientDAO.find(rs.getInt("f.id_client")),
                                this.utilisateurDAO.find(rs.getInt("f.id_utilisateur")),
                                new HashMap<>()
                        );
                }
                if (rs.getInt("f.id_facture") == facture.get_idFacture()){
                    tempProduct.put(this.produitDAO.find(rs.getInt("pf.id_produit")),rs.getInt("pf.quant"));
                }
            }
            if(facture.get_idFacture()!=0){
                facture.set_produitsvendus(tempProduct);
                factures.add(facture);
            }
            ps.close();
            return factures;
        }


        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
