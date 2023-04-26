package com.example.stockproject.controller;

import java.util.List;

import com.example.stockproject.dao.implement.ClientDAO;
import com.example.stockproject.dao.implement.FactureDAO;
import com.example.stockproject.dao.implement.ProduitDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Client;
import com.example.stockproject.models.Facture;
import com.example.stockproject.models.Produit;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateScene;

import interfaces.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NewBillController implements ControllerInterface {

	@FXML
	private TableView<Facture> BillsTable;
	@FXML
	private TableColumn<Produit, String> nameColumn;
	@FXML
	private Label vendorLabel;
	@FXML
	private TextField QuantityLabel;
	@FXML
	private Button addProduct, confirm, quit;
	@FXML
	private ComboBox ClientsList, ProductsList;

	private Utilisateur user;
	private Facture facture;

	private ClientDAO clientsDAO = (ClientDAO) DAOFactory.getClientDao();
	private List<Client> clients = clientsDAO.findall();
	private ProduitDAO productsDAO = (ProduitDAO) DAOFactory.getProduitDao();
	private List<Produit> products = productsDAO.findall();
	private FactureDAO billsDAO = (FactureDAO) DAOFactory.getFactureDao();
	private List<Facture> bills = billsDAO.findall();

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		facture = new Facture();
		clients.forEach((x) -> ClientsList.getItems().add(x.get_nom()));
		products.forEach((x) -> ProductsList.getItems().add(x.get_nom()));

	}

	public void setUser(Utilisateur user) {
		this.user = user;
		vendorLabel.setText(user.get_login());
	}

	public void addToBill() {

		System.out.println(ProductsList.getValue());

	}

	/**
	 * Retour sur la page Home
	 */
	@FXML
	private void cancel() {

		ControllerInterface ctrl = new HomeController();
		CreateScene.createNewScene("Home", quit, "home", ctrl, user);
	}
}
