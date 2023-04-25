package com.example.stockproject.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Utilisateur {
    private IntegerProperty _idUtilisateur;
    private StringProperty _login;
    private StringProperty _password;
    private enum permissions{
        SELLER,
        MANAGER,
        ADMIN
    }
    private permissions _role;

    public Utilisateur() {
    	this._idUtilisateur = new SimpleIntegerProperty();
        this._login = new SimpleStringProperty();
        this._password = new SimpleStringProperty();
        this._role = setpermissions("SELLER");
    }
    
    public Utilisateur(int _idUtilisateur, String _login, String _password,String _role) {
        this._idUtilisateur = new SimpleIntegerProperty(_idUtilisateur);
        this._login = new SimpleStringProperty(_login);
        this._password = new SimpleStringProperty(_password);
        this._role = setpermissions(_role);
    }

    /**
     * @param roletoparse
     * @return the role of the user
     * Uses the role in the field perms to determine what kind of user we are creating
     */
    private permissions setpermissions(String roletoparse){
        switch (roletoparse){
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
        return _idUtilisateur.get();
    }

    public void set_idUtilisateur(int _idUtilisateur) {
        this._idUtilisateur.set(_idUtilisateur);
    }
    
    public IntegerProperty get_idUtilisateurProperty() {
    	return _idUtilisateur;
    }

    public String get_login() {
        return _login.get();
    }

    public void set_login(String _login) {
        this._login.set(_login);
    }
    
    public StringProperty get_loginProperty() {
    	return _login;
    }

    public String get_password() {
        return _password.get();
    }

    public void set_password(String _password) {
        this._password.set(_password);
    }
    
    public StringProperty get_passwordProperty() {
    	return _password;
    }
}
