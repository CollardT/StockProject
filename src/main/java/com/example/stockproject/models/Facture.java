package com.example.stockproject.models;

import java.util.Date;
import java.util.HashMap;

public class Facture {
    private int _idFacture;
    private Client _Client;
    private Utilisateur _Utilisateur;

    //** Utilisation de HashMap pour récupérer les données de la facture
    private HashMap<Produit,Integer> _produitsvendus;

    public Facture(int _idFacture, Client _Client, Utilisateur _Utilisateur,HashMap<Produit,Integer> produitsvendus) {
        this._idFacture = _idFacture;
        this._Client = _Client;
        this._Utilisateur = _Utilisateur;
        this._produitsvendus = produitsvendus;
    }
    public Facture(){
        this._idFacture = 0;
    }
    public int get_idFacture() {
        return _idFacture;
    }

    public void set_idFacture(int _idFacture) {
        this._idFacture = _idFacture;
    }
    public Client get_Client() {
        return _Client;
    }

    public void set_Client(Client _Client) {
        this._Client = _Client;
    }

    public Utilisateur get_Utilisateur() {
        return _Utilisateur;
    }

    public void set_Utilisateur(Utilisateur _Utilisateur) {
        this._Utilisateur = _Utilisateur;
    }
    public HashMap<Produit, Integer> get_produitsvendus() {
        return _produitsvendus;
    }

    public void set_produitsvendus(HashMap<Produit, Integer> _produitsvendus) {
        this._produitsvendus = _produitsvendus;
    }

}
