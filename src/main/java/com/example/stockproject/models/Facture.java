package com.example.stockproject.models;

import java.util.Date;

public class Facture {
    private int _idFacture;
    private int _idClient;
    private int _idUtilisateur;
    private int _total;
    private Date _date;

    public Facture(int _idFacture, int _idClient, int _idUtilisateur, int _total, Date _date) {
        this._idFacture = _idFacture;
        this._idClient = _idClient;
        this._idUtilisateur = _idUtilisateur;
        this._total = _total;
        this._date = _date;
    }

    public int get_idFacture() {
        return _idFacture;
    }

    public void set_idFacture(int _idFacture) {
        this._idFacture = _idFacture;
    }

    public int get_idClient() {
        return _idClient;
    }

    public void set_idClient(int _idClient) {
        this._idClient = _idClient;
    }

    public int get_idUtilisateur() {
        return _idUtilisateur;
    }

    public void set_idUtilisateur(int _idUtilisateur) {
        this._idUtilisateur = _idUtilisateur;
    }

    public int get_total() {
        return _total;
    }

    public void set_total(int _total) {
        this._total = _total;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }
}
