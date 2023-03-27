package com.example.stockproject.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Client {
	
    private IntegerProperty _idClient;
    private StringProperty _nom;
    private StringProperty _NISS;
    private StringProperty _email;
    private StringProperty _adresse;
    private BooleanProperty _isActive;
    
    public Client() {
    	this._idClient = new SimpleIntegerProperty();
        this._nom = new SimpleStringProperty();
        this._NISS = new SimpleStringProperty();
        this._email = new SimpleStringProperty();
        this._adresse = new SimpleStringProperty();
        this._isActive= new SimpleBooleanProperty();
    }
    
    public Client(int _idClient, String _nom, String _NISS, String _email, String _adresse, boolean _isActive) {
        this._idClient = new SimpleIntegerProperty(_idClient);
        this._nom = new SimpleStringProperty(_nom);
        this._NISS = new SimpleStringProperty(_NISS);
        this._email = new SimpleStringProperty(_email);
        this._adresse = new SimpleStringProperty(_adresse);
        this._isActive= new SimpleBooleanProperty(_isActive);
    }

    public int get_idClient() {
        return _idClient.get();
    }

    public void set_idClient(int _idClient) {
        this._idClient.set(_idClient);
    }

    public IntegerProperty idClientProperty() {
    	return _idClient;
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

    public String get_NISS() {
        return _NISS.get();
    }

    public void set_NISS(String _NISS) {
        this._NISS.set(_NISS);
    }

    public StringProperty NISSProperty() {
    	return _NISS;
    }
    
    public String get_email() {
        return _email.get();
    }

    public void set_email(String _email) {
        this._email.set(_email);
    }
    
    public StringProperty emailProperty() {
    	return _email;
    }

    public String get_adresse() {
        return _adresse.get();
    }

    public void set_adresse(String _adresse) {
        this._adresse.set(_adresse);
    }
    
    public StringProperty adresseProperty() {
    	return _adresse;
    }
    
    public Boolean get_isActive() {
        return _isActive.get();
    }

    public void set_isActive(Boolean _isActive) {
        this._isActive.set(_isActive);
    }
    
    public BooleanProperty isActiveProperty() {
    	return _isActive;
    }
}
