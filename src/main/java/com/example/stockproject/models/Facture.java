package com.example.stockproject.models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Facture {

	private int _idFacture;
	private Client _client;
	private Utilisateur _utilisateur;
	private List<ProduitQuantite> _produitQuantite;

	private String _nameFacture;

	private ListProperty<ProduitQuantite> _produitQuantiteProperty;
	private IntegerProperty _idFactureProperty;
	private ObjectProperty<Client> _clientProperty;
	private ObjectProperty<Utilisateur> _utilisateurProperty;

	private StringProperty _nameFactureProperty;

	public Facture() {
		Produit p = new Produit("", 0);
		List<ProduitQuantite> list = new ArrayList<ProduitQuantite>();
		ProduitQuantite pq = new ProduitQuantite(p, 0);
		list.add(pq);
		this._produitQuantite = list;

		_produitQuantiteProperty = new SimpleListProperty<>(FXCollections.observableList(list));

	}

	public Facture(int _idFacture, Client _client, Utilisateur _utilisateur, List<ProduitQuantite> _produitQuantite) {
		this._idFacture = _idFacture;
		this._client = _client;
		this._utilisateur = _utilisateur;
		this._produitQuantite = _produitQuantite;
		this._nameFacture = "Test";

		_idFactureProperty = new SimpleIntegerProperty(_idFacture);
		_clientProperty = new SimpleObjectProperty<>(_client);
		_utilisateurProperty = new SimpleObjectProperty<>(_utilisateur);
		_produitQuantiteProperty = new SimpleListProperty<>(FXCollections.observableList(_produitQuantite));
		_nameFactureProperty = new SimpleStringProperty("Test");
	}

	public String get_nameFacture() {
		return _nameFacture;
	}

	public void set_nameFacture(String _nameFacture) {
		this._nameFacture = _nameFacture;
	}

	public String get_nameFactureProperty() {
		return _nameFactureProperty.get();
	}

	public void set_nameFactureProperty(String _nameFacture) {
		this._nameFactureProperty.set(_nameFacture);
	}

	public StringProperty nameFactureProperty() {
		return _nameFactureProperty;
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

	public ListProperty<ProduitQuantite> produitQuantiteProperty() {
		return _produitQuantiteProperty;
	}

	public void set_produitQuantiteProperty(ObservableList<ProduitQuantite> _produitQuantiteProperty) {
		this._produitQuantiteProperty.set(_produitQuantiteProperty);
	}

	public int get_idFactureProperty() {
		return _idFactureProperty.get();
	}

	public IntegerProperty idFactureProperty() {
		return _idFactureProperty;
	}

	public void set_idFactureProperty(int _idFacture) {
		this._idFactureProperty.set(_idFacture);
	}

	public Client get_clientProperty() {
		return _clientProperty.get();
	}

	public ObjectProperty<Client> clientProperty() {
		return _clientProperty;
	}

	public void set_clientProperty(Client _clientProperty) {
		this._clientProperty.set(_clientProperty);
	}

	public Utilisateur get_utilisateurProperty() {
		return _utilisateurProperty.get();
	}

	public ObjectProperty<Utilisateur> utilisateurProperty() {
		return _utilisateurProperty;
	}

	public void set_utilisateurProperty(Utilisateur _utilisateurProperty) {
		this._utilisateurProperty.set(_utilisateurProperty);
	}

}