package com.example.stockproject.dao.implement;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends DAO<Client> {

    public ClientDAO(Connection conn){
        super(conn);
    }
    @Override
    public boolean create(Client obj) {
        try {
            PreparedStatement state = conn.prepareStatement("INSERT INTO client (nom,NISS,email,adresse,isActive) VALUES (?,?,?,?,?)");
            state.setString(1, obj.get_nom());
            state.setString(2, obj.get_NISS());
            state.setString(3, obj.get_email());
            state.setString(4, obj.get_adresse());
            state.setBoolean(5, true);
            state.executeUpdate();
            state.close();
            return true;

        }
        catch (SQLException e){
            System.out.println("Vous ne pouvez pas créer le client suivant:" + obj.get_nom());
            return false;
        }
    }

    @Override
    public boolean delete(Client obj) {
        try {
            PreparedStatement state = conn.prepareStatement("UPDATE client SET nom=?, NISS=?, email=?, adresse=?, isActive=?  WHERE client.id_client = ?");
            state.setString(1,obj.get_nom());
            state.setString(2,obj.get_NISS());
            state.setString(3,obj.get_email());
            state.setString(4,obj.get_adresse());
            state.setBoolean(5,false);
            state.setInt(6,obj.get_idClient());
            state.executeUpdate();
            state.close();
            return true;
        }
        catch (SQLException e){
            System.out.println("Probleme d'update du client avec l'id:"+obj.get_idClient());
            return false;
        }
    }

    @Override
    public boolean update(Client obj) {
        try {
            PreparedStatement state = conn.prepareStatement("UPDATE client SET nom=?, NISS=?, email=?, adresse=?, isActive=?  WHERE client.id_client = ?");
            state.setString(1,obj.get_nom());
            state.setString(2,obj.get_NISS());
            state.setString(3,obj.get_email());
            state.setString(4,obj.get_adresse());
            state.setBoolean(5,obj.get_isActive());
            state.setInt(6,obj.get_idClient());
            state.executeUpdate();
            state.close();
            return true;

        }
        catch (SQLException e){
            System.out.println("Probleme d'update du client avec l'id:"+obj.get_idClient());
            return false;
        }

    }

    /**
     *
     * @param id : index de l'élément à chercher
     * @return
     */
    @Override
    public Client find(int id) {
        Client client = null;
        try{
            PreparedStatement state = conn.prepareStatement("SELECT * FROM client c WHERE c.id_client=?");
            state.setInt(1, id);
            ResultSet result =state.executeQuery();
            if(result.next() ) {
                client = new Client(id, result.getString("nom"), result.getString( "NISS"), result.getString("email"),result.getString("adresse"),result.getBoolean("isActive"));
            }
        }
        catch (SQLException e){
            System.out.println("Probleme de récupération du client avec l'id:"+id);
        }
        return client;
    }

    public Client findbyname(String name) {
        Client client = null;
        try{
            PreparedStatement state = conn.prepareStatement("SELECT * FROM client c WHERE c.nom=?");
            state.setString(1, name);
            ResultSet result =state.executeQuery();
            if(result.first() ) {
                client = new Client(result.getInt("id_client"), result.getString("nom"), result.getString( "NISS"), result.getString("email"),result.getString("adresse"),result.getBoolean("isActive"));
            }
        }
        catch (SQLException e){
            System.out.println("Probleme de récupération du client avec le nom:"+name);
        }
        return client;
    }

    @Override
    public List<Client> findall() {
        List<Client> clients = new ArrayList<>();
        try {
            PreparedStatement state = conn.prepareStatement("SELECT * FROM client c Where isActive=1");
            ResultSet list = state.executeQuery();
            while (list.next()){
                clients.add(
                        new Client(list.getInt("id_client"), list.getString("nom"), list.getString( "NISS"), list.getString("email"),list.getString("adresse"),list.getBoolean("isActive"))
                );
            }
            state.close();
            return clients;
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
