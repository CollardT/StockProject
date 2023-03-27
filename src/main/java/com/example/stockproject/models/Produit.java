package com.example.stockproject.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produit {
	
    private IntegerProperty _idproduit;
    private StringProperty _nom;
    private IntegerProperty _stock;
    private BooleanProperty _isActive;
	
    public Produit () {
        this._idproduit = new SimpleIntegerProperty();
        this._nom = new SimpleStringProperty();
        this._stock = new SimpleIntegerProperty();
        this._isActive = new SimpleBooleanProperty();
    }
    
    public Produit(int _idproduit, String _nom, int _stock) {
        this._idproduit = new SimpleIntegerProperty(_idproduit);
        this._nom = new SimpleStringProperty(_nom);
        this._stock = new SimpleIntegerProperty(_stock);
        this._isActive = new SimpleBooleanProperty(true);
    }
    
    public Produit(int _idproduit, String _nom, int _stock, boolean _isActive) {
        this._idproduit = new SimpleIntegerProperty(_idproduit);
        this._nom = new SimpleStringProperty(_nom);
        this._stock = new SimpleIntegerProperty(_stock);
        this._isActive = new SimpleBooleanProperty(_isActive);
    }
    public int get_idproduit() {
        return _idproduit.get();
    }

    public void set_idproduit(int _idproduit) {
        this._idproduit.set(_idproduit);
    }
    
    public IntegerProperty idProduitProperty() {
    	return _idproduit;
    }

    public String get_nom() {
        return _nom.get();
    }

    public void set_nom(String _nom) {
        this._nom.set(_nom);
    }
    
    public StringProperty nomProperty() {
    	return _nom;
    }

    public int get_stock() {
        return _stock.get();
    }

    public void set_stock(int _stock) {
        this._stock.set(_stock);
    }

    public IntegerProperty stockProperty() {
    	return _stock;
    }
    
    public boolean get_isActive() {
        return _isActive.get();
    }

    public void set_isActive(boolean _isActive) {
        this._isActive.set(_isActive);
    }
    
    public BooleanProperty iActiveProperty() {
    	return _isActive;
    }
}