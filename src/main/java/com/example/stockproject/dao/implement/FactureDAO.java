package com.example.stockproject.dao.implement;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.models.Facture;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

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
        return false;
    }

    @Override
    public boolean delete(Facture obj) {
        return false;
    }

    @Override
    public boolean update(Facture obj) {
        return false;
    }
    // TODO PULL FROM DATABASE: Produit name id an other stuff
    // TODO AND other stuff trhough the appropriate DAOs
    @Override
    public Facture find(int idFacture) {
        return null;
    }

    @Override
    public List<Facture> findall() {
        return null;
    }
}
