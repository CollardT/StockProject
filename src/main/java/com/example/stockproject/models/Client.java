package com.example.stockproject.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Client {


    private Integer _idClient;
    private String _nomClient;
    private String _NISS;
    private String _email;
    private String _adresse;
    private boolean _isActive;

    public int get_idClientProperty() {
        return _idClientProperty.get();
    }

    public IntegerProperty _idClientProperty() {
        return _idClientProperty;
    }

    public void set_idClientProperty(int _idClientProperty) {
        this._idClientProperty.set(_idClientProperty);
    }

    public String get_nomProperty() {
        return _nomProperty.get();
    }

    public StringProperty _nomPropertyProperty() {
        return _nomProperty;
    }

    public void set_nomProperty(String _nomProperty) {
        this._nomProperty.set(_nomProperty);
    }

    public String get_NISSProperty() {
        return _NISSProperty.get();
    }

    public StringProperty _NISSProperty() {
        return _NISSProperty;
    }

    public void set_NISSProperty(String _NISSProperty) {
        this._NISSProperty.set(_NISSProperty);
    }

    public String get_emailProperty() {
        return _emailProperty.get();
    }

    public StringProperty _emailProperty() {
        return _emailProperty;
    }

    public void set_emailProperty(String _emailProperty) {
        this._emailProperty.set(_emailProperty);
    }

    public String get_adresseProperty() {
        return _adresseProperty.get();
    }

    public StringProperty _adresseProperty() {
        return _adresseProperty;
    }

    public void set_adresseProperty(String _adresseProperty) {
        this._adresseProperty.set(_adresseProperty);
    }

    public boolean is_isActiveProperty() {
        return _isActiveProperty.get();
    }

    public BooleanProperty _isActiveProperty() {
        return _isActiveProperty;
    }

    public void set_isActiveProperty(boolean _isActiveProperty) {
        this._isActiveProperty.set(_isActiveProperty);
    }

    private IntegerProperty _idClientProperty;
    private StringProperty _nomProperty;
    private StringProperty _NISSProperty;
    private StringProperty _emailProperty;
    private StringProperty _adresseProperty;
    private BooleanProperty _isActiveProperty;

    public Client(Integer _idClient, String _nomClient, String _NISS, String _email, String _adresse, boolean _isActive) {
        this._idClient = _idClient;
        this._nomClient = _nomClient;
        this._NISS = _NISS;
        this._email = _email;
        this._adresse = _adresse;
        this._isActive = _isActive;
        this._idClientProperty = new SimpleIntegerProperty(_idClient);
        this._nomProperty = new SimpleStringProperty(_nomClient);
        this._NISSProperty = new SimpleStringProperty(_NISS);
        this._emailProperty = new SimpleStringProperty(_email);
        this._adresseProperty = new SimpleStringProperty(_adresse);
        this._isActiveProperty = new SimpleBooleanProperty(_isActive);
    }
    public Client() {
        this._idClientProperty = new SimpleIntegerProperty();
        this._nomProperty = new SimpleStringProperty();
        this._NISSProperty = new SimpleStringProperty();
        this._emailProperty = new SimpleStringProperty();
        this._adresseProperty = new SimpleStringProperty();
        this._isActiveProperty = new SimpleBooleanProperty();
    }
    public Integer get_idClient() {
        return _idClient;
    }

    public void set_idClient(Integer _idClient) {
        this._idClient = _idClient;
    }

    public String get_nomClient() {
        return _nomClient;
    }

    public void set_nomClient(String _nomClient) {
        this._nomClient = _nomClient;
    }

    public String get_NISS() {
        return _NISS;
    }

    public void set_NISS(String _NISS) {
        this._NISS = _NISS;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_adresse() {
        return _adresse;
    }

    public void set_adresse(String _adresse) {
        this._adresse = _adresse;
    }

    public boolean is_isActive() {
        return _isActive;
    }

    public void set_isActive(boolean _isActive) {
        this._isActive = _isActive;
    }
}
