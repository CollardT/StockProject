package com.example.stockproject.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {

	// Déclaration des paramètres de la classe
	private int _idUtilisateur;
	private String _login, _password;
	private boolean _isActive;

	// Déclaration des paramètres de la classe pour utilisation JAVAFX
	private IntegerProperty _idUtilisateurProperty;
	private StringProperty _loginProperty, _passwordProperty;
	private BooleanProperty _isActiveProperty;

	private enum permissions {
		SELLER, MANAGER, ADMIN
	}

	private permissions _role;

	public Utilisateur() {
		this._role = setpermissions("SELLER");
	}

	public Utilisateur(String _login, String _password) {
		this._login = _login;
		this._password = _password;

		this._loginProperty = new SimpleStringProperty(_login);
		this._passwordProperty = new SimpleStringProperty(_password);
		this._role = setpermissions("SELLER");
	}

	public Utilisateur(int _idUtilisateur, String _login, String _password, String _role, boolean _isActive) {
		this._idUtilisateur = _idUtilisateur;
		this._login = _login;
		this._password = _password;
		this._isActive = _isActive;

		this._idUtilisateurProperty = new SimpleIntegerProperty(_idUtilisateur);
		this._loginProperty = new SimpleStringProperty(_login);
		this._passwordProperty = new SimpleStringProperty(_password);
		this._role = setpermissions(_role);
		this._isActiveProperty = new SimpleBooleanProperty(_isActive);
	}

	/**
	 * @param roletoparse
	 * @return the role of the user Uses the role in the field perms to determine
	 *         what kind of user we are creating
	 */
	private permissions setpermissions(String roletoparse) {
		switch (roletoparse) {
		case "SELLER":
			return permissions.SELLER;
		case "MANAGER":
			return permissions.MANAGER;
		case "ADMIN":
			return permissions.ADMIN;
		default:
			return permissions.SELLER;
		}
	}

	public String get_role() {
		return _role.name();
	}

	public int get_idUtilisateur() {
		return _idUtilisateur;
	}

	public void set_idUtilisateur(int _idUtilisateur) {
		this._idUtilisateur = _idUtilisateur;
	}

	public int get_idUtilisateurProperty() {
		return _idUtilisateurProperty.get();
	}

	public void set_idUtilisateurProperty(int _idUtilisateur) {
		this._idUtilisateurProperty.set(_idUtilisateur);
	}

	public IntegerProperty idUtilisateurProperty() {
		return _idUtilisateurProperty;
	}

	public String get_login() {
		return _login;
	}

	public void set_login(String _login) {
		this._login = _login;
	}

	public String get_loginProperty() {
		return _loginProperty.get();
	}

	public void set_loginProperty(String _login) {
		this._loginProperty.set(_login);
	}

	public StringProperty loginProperty() {
		return _loginProperty;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_passwordProperty() {
		return _passwordProperty.get();
	}

	public void set_passwordProperty(String _password) {
		this._passwordProperty.set(_password);
	}

	public StringProperty passwordProperty() {
		return _passwordProperty;
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

	public BooleanProperty isActiveProperty() {
		return _isActiveProperty;
	}
}
