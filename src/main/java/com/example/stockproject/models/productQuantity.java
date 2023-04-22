package com.example.stockproject.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class productQuantity {

	// Déclaration des paramètres de la classe
	private int _idFacture, _idProduct, _quantity;

	// Déclaration des paramètres de la classe pour utilisation JAVAFX
	private IntegerProperty _idFactureProperty, _idProductProperty, _quantityProperty;

	public productQuantity() {
	}

	public productQuantity(int _idFacture, int _idProduct, int _quantity) {
		this._idFacture = _idFacture;
		this._idProduct = _idProduct;
		this._quantity = _quantity;

		this._idFactureProperty = new SimpleIntegerProperty(_idFacture);
		this._idProductProperty = new SimpleIntegerProperty(_idProduct);
		this._quantityProperty = new SimpleIntegerProperty(_quantity);
	}

	public int get_idFacture() {
		return _idFacture;
	}

	public void set_idFacture(int _idFacture) {
		this._idFacture = _idFacture;
	}

	public int get_idFactureProperty() {
		return _idFactureProperty.get();
	}

	public void set_idFactureProperty(int _idFacture) {
		this._idFactureProperty.set(_idFacture);
	}

	public IntegerProperty idFactureProperty() {
		return _idFactureProperty;
	}

	public int get_idProduct() {
		return _idProduct;
	}

	public void set_idProduct(int _idProduct) {
		this._idProduct = _idProduct;
	}

	public int get_idProductProperty() {
		return _idProductProperty.get();
	}

	public void set_idProductProperty(int _idProduct) {
		this._idProductProperty.set(_idProduct);
	}

	public IntegerProperty idProductProperty() {
		return _idProductProperty;
	}

	public int get_quantity() {
		return _quantity;
	}

	public void set_quantity(int _quantity) {
		this._quantity = _quantity;
	}

	public int get_quantityProperty() {
		return _quantityProperty.get();
	}

	public void set_quantityProperty(int _quantity) {
		this._quantityProperty.set(_quantity);
	}

	public IntegerProperty quantityProperty() {
		return _quantityProperty;
	}
}
