package com.example.stockproject.models;

import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableMap;

public class Facture {
	private int _idFacture;
	private Client _Client;
	private Utilisateur _Utilisateur;
	
    private IntegerProperty _idFactureProperty;
    private ObjectProperty<Client> _ClientProperty;
    private ObjectProperty<Utilisateur> _UtilisateurProperty;
    
    public Facture(int _idFacture, Client _Client, Utilisateur _Utilisateur, HashMap<Produit,Integer> produitsvendus) {
    	this._idFacture = _idFacture;
    	this._Client = _Client;
    	this._Utilisateur = _Utilisateur;
    	
        this._idFactureProperty = new SimpleIntegerProperty(_idFacture);
        this._ClientProperty = new SimpleObjectProperty<>(_Client);
        this._UtilisateurProperty = new SimpleObjectProperty<>(_Utilisateur);
        this._produitsvendus = produitsvendus;
        produitsvendus.forEach((k,v) -> ProduitVendusProperty.put(k,v));
    }
    
    private HashMap<Produit, Integer> _produitsvendus;
    
    public HashMap<Produit, Integer> get_produitsvendus() {
        return _produitsvendus;
    }

    public void set_produitsvendus(HashMap<Produit, Integer> _produitsvendus) {
        this._produitsvendus = _produitsvendus;
    }


    public ObservableMap<Produit, Integer> getProduitVendusProperty() {
        return ProduitVendusProperty.get();
    }

    public SimpleMapProperty<Produit, Integer> produitVendusPropertyProperty() {
        return ProduitVendusProperty;
    }

    public void setProduitVendusProperty(ObservableMap<Produit, Integer> produitVendusProperty) {
        this.ProduitVendusProperty.set(produitVendusProperty);
    }

    SimpleMapProperty<Produit, Integer> ProduitVendusProperty = new SimpleMapProperty<>(FXCollections.observableHashMap());
    
    public Facture(){
        this._idFacture = 0;
    }
    
    public int get_idFacture() {
        return _idFacture;
    }
    
    public void set_idFacture(int _idFacture) {
        this._idFacture = _idFacture;
    }
    
    public int get_idFactureProperty() {
    	return _idFactureProperty.get();
    }
    
    public void set_idFactureProperty(int _idFactureProperty) {
    	this._idFactureProperty.set(_idFactureProperty);
    }
    
    public IntegerProperty idFactureProperty() {
    	return _idFactureProperty;
    }

    public Client get_Client() {
        return _Client;
    }

    public void set_Client(Client _Client) {
        this._Client = _Client;
    }
    
    public Client get_ClientProperty() {
    	return _ClientProperty.get();
    }
    
    public void set_ClientProperty(Client _ClientProperty) {
    	this._ClientProperty.set(_ClientProperty);
    }
    
    public ObjectProperty<Client> ClientProperty(){
    	return _ClientProperty;
    }

    public Utilisateur get_Utilisateur() {
        return _Utilisateur;
    }

    public void set_Utilisateur(Utilisateur _Utilisateur) {
        this._Utilisateur = _Utilisateur;
    }
    
    public Utilisateur get_UtilisateurProperty() {
    	return _UtilisateurProperty.get();
    }
    
    public void set_UtilisateurProperty(Utilisateur _UtilisateurProperty) {
    	this._UtilisateurProperty.set(_UtilisateurProperty);
    }
    
    public ObjectProperty<Utilisateur> UtilisateurProprety(){
    	return _UtilisateurProperty;
    }
}
