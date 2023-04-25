package com.example.stockproject.models;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Facture {
	public Facture(int _idFacture, Client _client, Utilisateur _utilisateur, List<ProduitQuantite> _produitQuantite) {
		this._idFacture = _idFacture;
		this._client = _client;
		this._utilisateur = _utilisateur;
		this._produitQuantite = _produitQuantite;

		_idFactureProperty = new SimpleIntegerProperty(_idFacture);
		_clientProperty = new SimpleObjectProperty<>(_client);
		_utilisateurProperty = new SimpleObjectProperty<>(_utilisateur);
		_produitQuantiteProperty = new SimpleListProperty<>(FXCollections.observableList(_produitQuantite));
	}

	public Facture() {
		this._idFacture = 0;
	}

	public int get_idFacture() {
		return _idFacture;
	}

	public void set_idFacture(int _idFacture) {
		this._idFacture = _idFacture;
	}

	public Client get_client() {
		return _client;
	}

	public void set_client(Client _client) {
		this._client = _client;
	}

	public Utilisateur get_utilisateur() {
		return _utilisateur;
	}

	public void set_utilisateur(Utilisateur _utilisateur) {
		this._utilisateur = _utilisateur;
	}

	public List<ProduitQuantite> get_produitQuantite() {
		return _produitQuantite;
	}

	public void set_produitQuantite(List<ProduitQuantite> _produitQuantite) {
		this._produitQuantite = _produitQuantite;
	}

	public ObservableList<ProduitQuantite> get_produitQuantiteProperty() {
		return _produitQuantiteProperty.get();
	}

	public ListProperty<ProduitQuantite> _produitQuantitePropertyProperty() {
		return _produitQuantiteProperty;
	}

	public void set_produitQuantiteProperty(ObservableList<ProduitQuantite> _produitQuantiteProperty) {
		this._produitQuantiteProperty.set(_produitQuantiteProperty);
	}

	public int get_idFactureProperty() {
		return _idFactureProperty.get();
	}

	public IntegerProperty _idFacturePropertyProperty() {
		return _idFactureProperty;
	}

	public void set_idFactureProperty(int _idFactureProperty) {
		this._idFactureProperty.set(_idFactureProperty);
	}

	private int _idFacture;
	private Client _client;
	private Utilisateur _utilisateur;
	private List<ProduitQuantite> _produitQuantite;

	public Client get_clientProperty() {
		return _clientProperty.get();
	}

	public ObjectProperty<Client> _clientPropertyProperty() {
		return _clientProperty;
	}

	public void set_clientProperty(Client _clientProperty) {
		this._clientProperty.set(_clientProperty);
	}

	public Utilisateur get_utilisateurProperty() {
		return _utilisateurProperty.get();
	}

	public ObjectProperty<Utilisateur> _utilisateurPropertyProperty() {
		return _utilisateurProperty;
	}

	public void set_utilisateurProperty(Utilisateur _utilisateurProperty) {
		this._utilisateurProperty.set(_utilisateurProperty);
	}

	private ListProperty<ProduitQuantite> _produitQuantiteProperty;
	private IntegerProperty _idFactureProperty;
	private ObjectProperty<Client> _clientProperty;
	private ObjectProperty<Utilisateur> _utilisateurProperty;

}