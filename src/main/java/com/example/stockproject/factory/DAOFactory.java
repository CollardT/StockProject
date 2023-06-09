package com.example.stockproject.factory;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.dao.implement.ClientDAO;
import com.example.stockproject.dao.implement.FactureDAO;
import com.example.stockproject.dao.implement.ProduitDAO;
import com.example.stockproject.dao.implement.UtilisateurDAO;
import com.example.stockproject.models.Client;
import com.example.stockproject.models.Facture;
import com.example.stockproject.models.Produit;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.singleton.DBConnectionSingleton;

import java.sql.Connection;

public class DAOFactory {
    protected static final Connection conn = DBConnectionSingleton.getInstance();

    public static DAO<Client> getClientDao(){
        return new ClientDAO(conn);
    }
    public static DAO<Produit> getProduitDao(){return new ProduitDAO(conn);}
    public static DAO<Facture> getFactureDao(){return new FactureDAO(conn);}

    public static DAO<Utilisateur> getUtilisateurDao(){return new UtilisateurDAO(conn);}


}