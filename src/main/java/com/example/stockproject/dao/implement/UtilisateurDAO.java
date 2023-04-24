package com.example.stockproject.dao.implement;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Client;
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
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO utilisateur (login, password, permissions) VALUES (?,?,?)");
            ps.setString(1, obj.get_login());
            ps.setString(2, obj.get_password());
            ps.setString(3, obj.get_role());
            ps.executeUpdate();
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
    public boolean delete(Utilisateur obj) {
        try {
            if(checkforuser(obj))
            {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("UPDATE utilisateur SET isActive = ? WHERE id_utilisateur =?");
                ps.setBoolean(1, false);
                ps.setInt(2, obj.get_idUtilisateur());
                ps.executeUpdate();
                ps.close();
                conn.commit();
                conn.setAutoCommit(true);
                return true;
            }
            else {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur =?");
                ps.setInt(1, obj.get_idUtilisateur());
                ps.executeUpdate();
                ps.close();
                conn.commit();
                conn.setAutoCommit(true);
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Utilisateur obj) {
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("UPDATE utilisateur SET login =?, password =?, permissions =? WHERE id_utilisateur =?");
            ps.setString(1, obj.get_login());
            ps.setString(2, obj.get_password());
            ps.setString(3, obj.get_role());
            ps.setInt(4, obj.get_idUtilisateur());
            ps.executeUpdate();
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

    /**
     * Retourne un utilisateur de la base de données
     * @param id : index de l'utilisateur à chercher
     * @return
     */
    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = null;
        try {
            conn.setAutoCommit(false);
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
                conn.commit();
                conn.setAutoCommit(true);
                return utilisateur;
            }
            else {
                conn.commit();
                conn.setAutoCommit(true);
                return null;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Utilisateur findByName(String name) {
        Utilisateur utilisateur = null;
        try {
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur WHERE login =?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                utilisateur = new Utilisateur(
                rs.getInt("id_utilisateur"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("permissions")
                );
                conn.commit();
                conn.setAutoCommit(true);
                return utilisateur;
            }
            else {
                conn.commit();
                conn.setAutoCommit(true);
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
            conn.setAutoCommit(false);
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
            conn.commit();
            conn.setAutoCommit(true);
            return utilisateurs;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean checkforuser(Utilisateur obj){
        try{
            conn.setAutoCommit(false);
            PreparedStatement state = conn.prepareStatement("SELECT * FROM facture WHERE id_utilisateur=?");
            state.setInt(1,obj.get_idUtilisateur());
            ResultSet rs = state.executeQuery();
            if(rs.next()){
                state.close();
                conn.commit();
                conn.setAutoCommit(true);
                return true;
            }
            state.close();
            conn.commit();
            conn.setAutoCommit(true);
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }
}
