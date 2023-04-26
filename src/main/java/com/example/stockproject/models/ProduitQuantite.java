package com.example.stockproject.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ProduitQuantite {

	private Produit produit;
	private int quantite;

	private ObjectProperty<Produit> produitProperty;
	private ObjectProperty<Integer> quantiteProperty;

	public ProduitQuantite() {

	}

	public ProduitQuantite(Produit produit, int quantite) {
		this.produit = produit;
		this.quantite = quantite;

		produitProperty = new SimpleObjectProperty<>(produit);
		quantiteProperty = new SimpleIntegerProperty(quantite).asObject();
	}

	public int getQuantiteProperty() {
		return quantiteProperty.get();
	}

	public ObjectProperty<Integer> quantiteProperty() {
		return quantiteProperty;
	}

	public void setQuantiteProperty(int quantiteProperty) {
		this.quantiteProperty.set(quantiteProperty);
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Produit getProduitProperty() {
		return produitProperty.get();
	}

	public ObjectProperty<Produit> produitProperty() {
		return produitProperty;
	}

	public void setProduitProperty(Produit produitProperty) {
		this.produitProperty.set(produitProperty);
	}
}