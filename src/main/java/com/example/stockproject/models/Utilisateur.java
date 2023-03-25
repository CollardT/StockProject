package com.example.stockproject.models;

public class Utilisateur {
    private int _idUtilisateur;
    private String _login;
    private String _password;
    private enum permissions{
        SELLER,
        MANAGER,
        ADMIN
    }
    private permissions _role;

    public Utilisateur(int _idUtilisateur, String _login, String _password,String _role) {
        this._idUtilisateur = _idUtilisateur;
        this._login = _login;
        this._password = _password;
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
        return _idUtilisateur;
    }

    public void set_idUtilisateur(int _idUtilisateur) {
        this._idUtilisateur = _idUtilisateur;
    }

    public String get_login() {
        return _login;
    }

    public void set_login(String _login) {
        this._login = _login;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
