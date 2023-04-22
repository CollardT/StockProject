package com.example.stockproject.controller;

import java.io.IOException;

import com.example.stockproject.Main;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateScene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class HomeController {

	// Déclaration des éléments FXML lié au controlleur
	@FXML
	private Button clients, products, bills, newBill, newUser, quit;

	private Utilisateur user;

	/**
	 * Ouverture de la page création d'un client
	 * 
	 */
	@FXML
	private void openClientsList() {
//		try {
//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Clients.fxml"));
//			AnchorPane clientsList = (AnchorPane) loader.load();
//			Stage root = (Stage) clients.getScene().getWindow();
//			root.setScene(new Scene(clientsList));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		CreateScene.createNewScene("Clients", clients, "clients");
	}

	/**
	 * Ouverture de la page de la liste des produits
	 * 
	 */
	@FXML
	private void openProductsList() {
//		try {
//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Products.fxml"));
//			AnchorPane productsList = (AnchorPane) loader.load();
//			Stage root = (Stage) products.getScene().getWindow();
//			root.setScene(new Scene(productsList));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		CreateScene.createNewScene("Products", products, "products");
	}

	/**
	 * Ouverture de la page de la liste des factures
	 * 
	 */
	@FXML
	private void openBillsList() {
//		try {
//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Bills.fxml"));
//			AnchorPane billsList = (AnchorPane) loader.load();
//			Stage root = (Stage) bills.getScene().getWindow();
//			root.setScene(new Scene(billsList));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		CreateScene.createNewScene("Bills", bills, "bills");
	}

	/**
	 * Ouverture de la page de création d'une nouvelle facture
	 * 
	 */
	@FXML
	private void openNewBill() throws IOException {
//		try {
//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("NewBill.fxml"));
//			AnchorPane newBillWindow = (AnchorPane) loader.load();
//			Stage root = (Stage) newBill.getScene().getWindow();
//			root.setScene(new Scene(newBillWindow));
//
//			NewBillController controller = loader.getController();
//			controller.setUser(this.user);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		CreateScene.createNewScene("NewBill", newBill, "newBill");

		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Home.fxml"));
		loader.load();
		NewBillController controller = loader.getController();
		controller.setUser(this.user);
	}

	/**
	 * Ouverture de la page de création d'un Utilisateur
	 * 
	 */
	@FXML
	private void openNewUser() {
//		try {
//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("NewUser.fxml"));
//			AnchorPane newUserWindow = (AnchorPane) loader.load();
//			Stage root = (Stage) newUser.getScene().getWindow();
//			root.setScene(new Scene(newUserWindow));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		CreateScene.createNewScene("NewUser", newUser, "newUser");
	}

	/**
	 * Déconnecte l'utilisateur et renvoie à la page de Login
	 * 
	 */
	@FXML
	private void disconnect() {
//		try {
//			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Login.fxml"));
//			AnchorPane login = (AnchorPane) loader.load();
//			Stage root = (Stage) quit.getScene().getWindow();
//			root.setScene(new Scene(login));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		CreateScene.createNewScene("Login", quit, "login");
	}

	/**
	 * stock la valeur de l'utilisateur connecté
	 * 
	 * @param user
	 */
	public void setUser(Utilisateur user) {
		this.user = user;
		this.user.set_login(user.get_login());
	}
}
