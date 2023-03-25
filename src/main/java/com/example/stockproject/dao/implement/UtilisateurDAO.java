package com.example.stockproject.dao.implement;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur> {

    public UtilisateurDAO(Connection conn) {
        super(conn);
    }

    @Override
    public boolean create(Utilisateur obj) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO utilisateur (login, password, permissions)");
            ps.setString(1, obj.get_login());
            ps.setString(2, obj.get_password());
            ps.setString(3, obj.get_role());
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
    public boolean delete(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE utilisateur SET login =?, password =?, permissions =? WHERE id =?");
            ps.setString(1, obj.get_login());
            ps.setString(2, obj.get_password());
            ps.setString(3, obj.get_role());
            ps.setInt(4, obj.get_idUtilisateur());
            ps.executeUpdate();
            ps.close();
            return true;

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Retourne un utilisateur de la base de données
     * @param id : index de l'utilisateur à chercher
     * @return
     */
    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur WHERE id_utilisateur =?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur(
                rs.getInt("id_utilisateur"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("permissions")
                );
                return utilisateur;
            }
            else {
                return null;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Utilisateur> findall() {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur");
            ResultSet rs = ps.executeQuery();
            List<Utilisateur> utilisateurs = new java.util.ArrayList<>();
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                rs.getInt("id"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("role")
                );
                utilisateurs.add(utilisateur);
            }
            return utilisateurs;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
