package com.example.stockproject.controller;

import java.util.List;

import com.example.stockproject.dao.implement.FactureDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Facture;
import com.example.stockproject.models.ProduitQuantite;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateAlert;
import com.example.stockproject.utilities.CreateScene;

import interfaces.ControllerInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BillsController implements ControllerInterface {

	@FXML
	private TableView<Facture> BillTable;
	@FXML
	private TableView<ProduitQuantite> ProductQuantityTable;
	@FXML
	private TableColumn<Facture, String> billColumn;
	@FXML
	private TableColumn<ProduitQuantite, String> productsColumn;
	@FXML
	private TableColumn<ProduitQuantite, Integer> quantityColumn;
	@FXML
	private Button quit;

	private FactureDAO factureDAO = (FactureDAO) DAOFactory.getFactureDao();
	private List<Facture> factures = factureDAO.findall();

	private Utilisateur user;

	@FXML
	private void initialize() {
		billColumn.setCellValueFactory(cellData -> cellData.getValue().nameFactureProperty());
		BillTable.setItems(FXCollections.observableList(factures));
		BillTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBillDetails(newValue));
	}

	/**
	 * Récupère les informations de la facture sélectionnée
	 *
	 * @param facture
	 */
	private void showBillDetails(Facture facture) {
		productsColumn.setCellValueFactory(cellData -> cellData.getValue().getProduitProperty().nomProperty());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty());
		if (facture != null) {
			ProductQuantityTable.setItems(FXCollections.observableList(facture.get_produitQuantite()));
		}
	}

	@FXML
	private void delete() {
		Facture selectedBill = BillTable.getSelectionModel().getSelectedItem();
		if (selectedBill != null) {
			ProductQuantityTable.getItems().clear();
			factureDAO.delete(selectedBill);
			factures = factureDAO.findall();
			BillTable.setItems(FXCollections.observableList(factures));
			CreateAlert.createAlert("INFORMATION", "Suppression facture", "Facture suprimée", null);
		} else {
			CreateAlert.createAlert("ERROR", "Empty Bill", "Pas de facture séléctionné",
					"Merci de sélectionner un facture dans la table.");
		}
	}

	/**
	 * Retour sur la page Home
	 */
	@FXML
	private void cancel() {
		ControllerInterface ctrl = new HomeController();
		CreateScene.createNewScene("Home", quit, "home", ctrl, user);
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
