package com.example.stockproject.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produit {
    private Integer _idproduit;
    private String _nom;



    private int _stock;
    private boolean _isActive;
    private IntegerProperty _idproduitProperty;
    private StringProperty _nomproduitProperty;
    private IntegerProperty _stockProperty;
    private BooleanProperty _isActiveProperty;
	
    public Produit () {
        this._idproduit = 0;
        this._nom = "";
        this._stock = 0;
        this._isActive = true;

        this._idproduitProperty = new SimpleIntegerProperty();
        this._nomproduitProperty = new SimpleStringProperty();
        this._stockProperty = new SimpleIntegerProperty();
        this._isActiveProperty = new SimpleBooleanProperty();
    }
    
    public Produit(int _idproduit, String _nom, int _stock) {
        this._idproduit = _idproduit;
        this._nom = _nom;
        this._stock = _stock;
        this._isActive = true;

        this._idproduitProperty = new SimpleIntegerProperty(_idproduit);
        this._nomproduitProperty = new SimpleStringProperty(_nom);
        this._stockProperty = new SimpleIntegerProperty(_stock);
        this._isActiveProperty = new SimpleBooleanProperty(true);
    }
    
    public Produit(int _idproduit, String _nom, int _stock, boolean _isActive) {
        this._idproduit = _idproduit;
        this._nom = _nom;
        this._stock = _stock;
        this._isActive = _isActive;

        this._idproduitProperty = new SimpleIntegerProperty(_idproduit);
        this._nomproduitProperty = new SimpleStringProperty(_nom);
        this._stockProperty = new SimpleIntegerProperty(_stock);
        this._isActiveProperty = new SimpleBooleanProperty(_isActive);
    }
    public Integer get_idproduit() {
        return _idproduit;
    }

    public void set_idproduit(Integer _idproduit) {
        this._idproduit = _idproduit;
    }

    public String get_nom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public int get_stock() {
        return _stock;
    }

    public void set_stock(int _stock) {
        this._stock = _stock;
    }

    public boolean is_isActive() {
        return _isActive;
    }

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }

    public int get_idproduitProperty() {
        return _idproduitProperty.get();
    }

    public IntegerProperty _idproduitPropertyProperty() {
        return _idproduitProperty;
    }

    public void set_idproduitProperty(int _idproduitProperty) {
        this._idproduitProperty.set(_idproduitProperty);
    }

    public String get_nomproduitProperty() {
        return _nomproduitProperty.get();
    }

    public StringProperty _nomproduitPropertyProperty() {
        return _nomproduitProperty;
    }

    public void set_nomproduitProperty(String _nomproduitProperty) {
        this._nomproduitProperty.set(_nomproduitProperty);
    }

    public int get_stockProperty() {
        return _stockProperty.get();
    }

    public IntegerProperty _stockPropertyProperty() {
        return _stockProperty;
    }

    public void set_stockProperty(int _stockProperty) {
        this._stockProperty.set(_stockProperty);
    }

    public boolean is_isActiveProperty() {
        return _isActiveProperty.get();
    }

    public BooleanProperty _isActivePropertyProperty() {
        return _isActiveProperty;
    }

    public void set_isActiveProperty(boolean _isActiveProperty) {
        this._isActiveProperty.set(_isActiveProperty);
    }

}