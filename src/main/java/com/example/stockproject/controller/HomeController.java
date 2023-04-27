package com.example.stockproject.controller;

import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateScene;

import interfaces.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController implements ControllerInterface {

	// Déclaration des éléments FXML lié au controlleur
	@FXML
	private Button clients, products, bills, newBill, newUser, quit, test;

	private Utilisateur user;

	/**
	 * Ouverture de la page création d'un client
	 * 
	 */
	@FXML
	private void openClientsList() {
		ControllerInterface ctrl = new ClientsController();
		CreateScene.createNewScene("Clients", clients, "clients", ctrl, user);
	}

	/**
	 * Ouverture de la page de la liste des produits
	 * 
	 */
	@FXML
	private void openProductsList() {
		ControllerInterface ctrl = new ProductsController();
		CreateScene.createNewScene("Products", products, "products", ctrl, user);
	}

	/**
	 * Ouverture de la page de la liste des factures
	 * 
	 */
	@FXML
	private void openBillsList() {
		ControllerInterface ctrl = new BillsController();
		CreateScene.createNewScene("Bills", bills, "bills", ctrl, user);
	}

	/**
	 * Ouverture de la page de création d'une nouvelle facture
	 * 
	 */
	@FXML
	private void openNewBill() {
		ControllerInterface ctrl = new NewBillController();
		CreateScene.createNewScene("NewBill", newBill, "newBill", ctrl, user);
	}

	/**
	 * Ouverture de la page de création d'un Utilisateur
	 * 
	 */
	@FXML
	private void openNewUser() {
		ControllerInterface ctrl = new NewUserController();
		CreateScene.createNewScene("NewUser", newUser, "newUser", ctrl, user);
	}

	/**
	 * Déconnecte l'utilisateur et renvoie à la page de Login
	 * 
	 */
	@FXML
	private void disconnect() {
		ControllerInterface ctrl = new LoginController();
		CreateScene.createNewScene("Login", quit, "login", ctrl, user);
	}

	/**
	 * stock la valeur de l'utilisateur connecté
	 * 
	 * @param user
	 */
	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
