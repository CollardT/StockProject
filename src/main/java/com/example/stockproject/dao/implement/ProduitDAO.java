package com.example.stockproject.dao.implement;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends DAO<Produit> {
    public ProduitDAO(Connection conn) { super(conn); }

    @Override
    public boolean create(Produit obj) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO produit (nom,stock,is_active) VALUES (?,?,?)");
            ps.setString(1, obj.get_nom());
            ps.setDouble(2, obj.get_stock());
            ps.setBoolean(3, obj.is_isActive());
            ps.executeUpdate();
            ps.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Produit obj) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE produit SET is_active = ? WHERE id_produit =?");
            ps.setBoolean(1, false);
            ps.setInt(2, obj.get_idproduit());
            ps.executeUpdate();
            ps.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Produit obj) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE produit SET nom =?, stock =? WHERE id_produit =?");
            ps.setString(1, obj.get_nom());
            ps.setInt(2, obj.get_stock());
            ps.setInt(3, obj.get_idproduit());
            ps.executeUpdate();
            ps.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Produit find(int id) {
        Produit produit = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit WHERE id_produit =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                produit = new Produit(
                rs.getInt("id_produit"),
                rs.getString("nom"),
                rs.getInt("stock"),
                rs.getBoolean("isActive"));
            }
            return produit;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Produit> findall() {
        List<Produit> produits;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produit WHERE is_active = 1");
            ResultSet rs = ps.executeQuery();
            produits = new ArrayList<>();
            while (rs.next()) {
                produits.add(new Produit(
                rs.getInt("id_produit"),
                rs.getString("nom"),
                rs.getInt("stock"),
                rs.getBoolean("is_active")));
            }
            return produits;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
