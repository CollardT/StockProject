package com.example.stockproject.controller;

import java.util.List;

import com.example.stockproject.dao.implement.ClientDAO;
import com.example.stockproject.dao.implement.FactureDAO;
import com.example.stockproject.dao.implement.ProduitDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Client;
import com.example.stockproject.models.Facture;
import com.example.stockproject.models.Produit;
import com.example.stockproject.models.ProduitQuantite;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateAlert;
import com.example.stockproject.utilities.CreateNewDate;
import com.example.stockproject.utilities.CreateScene;
import com.example.stockproject.utilities.IsANumber;

import interfaces.ControllerInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NewBillController implements ControllerInterface {

	@FXML
	private TableView<ProduitQuantite> BillsTable;
	@FXML
	private TableColumn<ProduitQuantite, String> productsColumn;
	@FXML
	private TableColumn<ProduitQuantite, Integer> quantityColumn;
	@FXML
	private Label vendorLabel;
	@FXML
	private TextField QuantityLabel;
	@FXML
	private Button quit;
	@FXML
	private ComboBox ClientsList, ProductsList;

	private Utilisateur user;
	private Facture facture;

	private ClientDAO clientDAO = (ClientDAO) DAOFactory.getClientDao();
	private List<Client> clients = clientDAO.findall();
	private ProduitDAO productDAO = (ProduitDAO) DAOFactory.getProduitDao();
	private List<Produit> products = productDAO.findall();
	private FactureDAO billDAO = (FactureDAO) DAOFactory.getFactureDao();

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		facture = new Facture();
		facture.get_produitQuantite().clear();
		clients.forEach((x) -> ClientsList.getItems().add(x.get_nom()));
		products.forEach((x) -> ProductsList.getItems().add(x.get_nom()));
		productsColumn.setCellValueFactory(cellData -> cellData.getValue().getProduitProperty().nomProperty());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantiteProperty());
		BillsTable.setItems(FXCollections.observableList(facture.get_produitQuantite()));

	}

	public void setUser(Utilisateur user) {
		this.user = user;
		vendorLabel.setText(user.get_login());
	}

	@FXML
	public void addToBill() {
		List<ProduitQuantite> list = facture.get_produitQuantite();
		Produit selectedProduct = productDAO.findByName(ProductsList.getSelectionModel().getSelectedItem().toString());
		String quantity = QuantityLabel.getText();
		int index = isInList(list, selectedProduct);

		if (checkField(selectedProduct)) {
			ProduitQuantite pq = new ProduitQuantite(selectedProduct, Integer.parseInt(quantity));
			if (index != -1) {
				int tempQuantity = list.get(index).getQuantite();
				list.remove(index);
				pq = new ProduitQuantite(selectedProduct, (tempQuantity + Integer.parseInt(quantity)));
				list.add(index, pq);
			} else {
				list.add(pq);
			}
			BillsTable.setItems(FXCollections.observableList(list));
		}
	}

	/**
	 * Fonction pour vérifier la valeur des champs (un produit doit être sélectionné
	 * et un NOMBRE entré pour la quantité)
	 *
	 * @return
	 */
	private boolean checkField(Produit selectedProduct) {

		String errorMessage = "";

		if (ProductsList.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "Veuillez séléctionner un produit\n";
		}
		if (QuantityLabel.getText() == "" || !IsANumber.isANumber(QuantityLabel.getText())) {
			errorMessage += "Quantité invalide, n'entrez que des chiffres\n";
		}
		if (IsANumber.isANumber(QuantityLabel.getText())) {
			if (Integer.parseInt(QuantityLabel.getText()) > selectedProduct.get_stock()) {
				errorMessage += "La quantité maximum est de : " + selectedProduct.get_stock() + "\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			CreateAlert.createAlert("ERROR", "Erreur d'encodage", "Merci de vérifier les champs invalides",
					errorMessage);
			return false;
		}
	}

	private boolean checkFieldCreate() {

		String errorMessage = "";

		if (ClientsList.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "Un client doit être sélectionné\n";
		}
		if (facture.get_produitQuantite().size() == 0) {
			errorMessage += "Au moins un produit doit être ajouté à la facture\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			CreateAlert.createAlert("ERROR", "Erreur d'encodage", "Merci de vérifier les champs invalides",
					errorMessage);
			return false;
		}
	}

	/**
	 * Fonction pour vérifier si Produit déjà dans la liste, renvoie l'index du
	 * produit dans la liste
	 */
	private int isInList(List<ProduitQuantite> list, Produit selectedProduct) {
		int result = 0;
		if (list.size() == 0) {
			return -1;
		} else {
			for (ProduitQuantite pq : list) {
				if (pq.getProduit().get_idProduit() != selectedProduct.get_idProduit() || list.size() == 0) {
					result = -1;
				} else {
					result = list.indexOf(pq);
					break;
				}
			}
		}
		return result;
	}

	@FXML
	private void createBill() {

		if (checkFieldCreate()) {

			Client selectedClient = clientDAO.findbyname(ClientsList.getSelectionModel().getSelectedItem().toString());
			this.facture.set_client(selectedClient);
			this.facture.set_produitQuantite(facture.get_produitQuantite());
			this.facture.set_utilisateur(user);
			this.facture.set_nameFacture(selectedClient.get_idClient() + "-" + selectedClient.get_nom().substring(0, 3)
					+ "-" + CreateNewDate.createNewDate());
			billDAO.create(this.facture);
			CreateAlert.createAlert("INFORMATION", "Facture encodée", "La facture à bien été crée", null);
			ClientsList.getSelectionModel().clearSelection();
			ProductsList.getSelectionModel().clearSelection();
			QuantityLabel.setText("");
			facture.get_produitQuantite().clear();
			BillsTable.setItems(FXCollections.observableList(facture.get_produitQuantite()));
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
}
