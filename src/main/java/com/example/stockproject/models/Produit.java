package com.example.stockproject.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produit {
	
	private int _idProduit;
	private String _nom;
	private int _stock;
	private boolean _isActive;
	
    private IntegerProperty _idProduitProperty;
    private StringProperty _nomProperty;
    private IntegerProperty _stockProperty;
    private BooleanProperty _isActiveProperty;
	
    public Produit () {
    	
    }
    
    public Produit(int _idProduit, String _nom, int _stock) {
    	this._idProduit = _idProduit;
    	this._nom = _nom;
    	this._stock = _stock;
    	this._isActive = true;
    	
        this._idProduitProperty = new SimpleIntegerProperty(_idProduit);
        this._nomProperty = new SimpleStringProperty(_nom);
        this._stockProperty = new SimpleIntegerProperty(_stock);
        this._isActiveProperty = new SimpleBooleanProperty(true);
    }
    
    public Produit(int _idProduit, String _nom, int _stock, boolean _isActive) {
    	this._idProduit = _idProduit;
    	this._nom = _nom;
    	this._stock = _stock;
    	this._isActive = _isActive;
    	
        this._idProduitProperty = new SimpleIntegerProperty(_idProduit);
        this._nomProperty = new SimpleStringProperty(_nom);
        this._stockProperty = new SimpleIntegerProperty(_stock);
        this._isActiveProperty = new SimpleBooleanProperty(_isActive);
    }
    public int get_idProduit() {
        return _idProduit;
    }

    public void set_idProduit(int _idProduit) {
        this._idProduit = _idProduit;
    }
    
    public int get_idProduitProperty() {
    	return _idProduitProperty.get();
    }
    
    public void set_idProduitProperty() {
    	this._idProduitProperty.set(_idProduit);
    }
    
    public IntegerProperty idProduitProperty() {
    	return _idProduitProperty;
    }
    
    public String get_nom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }
    
    public String get_nomProperty() {
    	return _nomProperty.get();
    }
    
    public void set_nomProperty() {
    	this._nomProperty.set(_nom);
    }
    
    public StringProperty nomProperty() {
    	return _nomProperty;
    }
    
    public int get_stock() {
    	return _stock;
    }
    
    public void set_stock(int _stock) {
    	this._stock = _stock;
    }

    public int get_stockPropetry() {
        return _stockProperty.get();
    }

    public void set_stockProperty(int _stock) {
        this._stockProperty.set(_stock);
    }

    public IntegerProperty stockProperty() {
    	return _stockProperty;
    }
    
    public boolean get_isActive() {
    	return _isActive;
    }
    
    public void set_isActive(boolean _isActive) {
    	this._isActive = _isActive;
    }
    
    public boolean get_isActiveProperty() {
        return _isActiveProperty.get();
    }

    public void set_isActiveProperty(boolean _isActive) {
        this._isActiveProperty.set(_isActive);
    }
    
    public BooleanProperty iActiveProperty() {
    	return _isActiveProperty;
    }
}