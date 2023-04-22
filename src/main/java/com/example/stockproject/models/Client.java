package com.example.stockproject.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {

	// Déclaration des paramètres de la classe
	private int _idClient;
	private String _nom, _NISS, _email, _adresse;
	private boolean _isActive;

	// Déclaration des paramètres de la classe pour utilisation JAVAFX
	private IntegerProperty _idClientProperty;
	private StringProperty _nomProperty, _NISSProperty, _emailProperty, _adresseProperty;
	private BooleanProperty _isActiveProperty;

	public Client() {
	}

	public Client(int _idClient, String _nom, String _NISS, String _email, String _adresse, boolean _isActive) {
		this._idClient = _idClient;
		this._nom = _nom;
		this._NISS = _NISS;
		this._email = _email;
		this._adresse = _adresse;
		this._isActive = _isActive;

		this._idClientProperty = new SimpleIntegerProperty(_idClient);
		this._nomProperty = new SimpleStringProperty(_nom);
		this._NISSProperty = new SimpleStringProperty(_NISS);
		this._emailProperty = new SimpleStringProperty(_email);
		this._adresseProperty = new SimpleStringProperty(_adresse);
		this._isActiveProperty = new SimpleBooleanProperty(_isActive);
	}

	public int get_idClient() {
		return _idClient;
	}

	public void set_idClient(int _idClient) {
		this._idClient = _idClient;
	}

	public int get_idClientProperty() {
		return _idClientProperty.get();
	}

	public void set_idClientProperty(int _idClient) {
		this._idClientProperty.set(_idClient);
	}

	public IntegerProperty idClientProperty() {
		return _idClientProperty;
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

	public void set_nomProperty(String _nom) {
		this._nomProperty.set(_nom);
	}

	public StringProperty nomProperty() {
		return _nomProperty;
	}

	public String get_NISS() {
		return _NISS;
	}

	public void set_NISS(String _NISS) {
		this._NISS = _NISS;
	}

	public String get_NISSProperty() {
		return _NISSProperty.get();
	}

	public void set_NISSProperty(String _NISS) {
		this._NISSProperty.set(_NISS);
	}

	public StringProperty NISSProperty() {
		return _NISSProperty;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public String get_emailProperty() {
		return _emailProperty.get();
	}

	public void set_emailProperty(String _email) {
		this._emailProperty.set(_email);
	}

	public StringProperty emailProperty() {
		return _emailProperty;
	}

	public String get_adresse() {
		return _adresse;
	}

	public void set_adresse(String _adresse) {
		this._adresse = _adresse;
	}

	public String get_adresseProperty() {
		return _adresseProperty.get();
	}

	public void set_adresseProperty(String _adresse) {
		this._adresseProperty.set(_adresse);
	}

	public StringProperty adresseProperty() {
		return _adresseProperty;
	}

	public boolean get_isActive() {
		return _isActive;
	}

	public void set_isActive(boolean _isActive) {
		this._isActive = _isActive;
	}

	public Boolean get_isActiveProperty() {
		return _isActiveProperty.get();
	}

	public void set_isActiveProperty(Boolean _isActive) {
		this._isActiveProperty.set(_isActive);
	}

	public BooleanProperty isActiveProperty() {
		return _isActiveProperty;
	}
}
