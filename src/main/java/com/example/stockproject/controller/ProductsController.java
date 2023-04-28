package com.example.stockproject.controller;

import java.io.IOException;
import java.util.List;

import com.example.stockproject.Main;
import com.example.stockproject.dao.implement.ProduitDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Produit;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateAlert;
import com.example.stockproject.utilities.CreateScene;

import interfaces.ControllerInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductsController implements ControllerInterface {

	@FXML
	private TableView<Produit> ProductTable;
	@FXML
	private TableColumn<Produit, String> nameColumn;
	@FXML
	private Label nameLabel;
	@FXML
	private Label stockLabel;
	@FXML
	private CheckBox isActiveCheckBox;
	@FXML
	private Button newProductWindow, edit, quit;

	private ProduitDAO productDAO = (ProduitDAO) DAOFactory.getProduitDao();
	private List<Produit> products = productDAO.findallActive();
	private Utilisateur user;

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		ProductTable.setItems(FXCollections.observableList(products));

		// Ajoute un écouteur pour afficher les infos du produit sélectionné
		ProductTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showProductDetails(newValue));
	}

	/**
	 * Fills all text fields to show details about the Product. If the specified
	 * Product is null, all text fields are cleared.
	 *
	 * @param Product the Product or null
	 */
	private void showProductDetails(Produit product) {
		if (product != null) {
			nameLabel.setText(product.get_nom());
			stockLabel.setText(Integer.toString(product.get_stock()));
			isActiveCheckBox.setSelected(product.get_isActive());
		} else {
			nameLabel.setText("");
			stockLabel.setText("");
			isActiveCheckBox.setSelected(false);
		}
	}

	@FXML
	public void showActiv() {
		refresh();
	}

	@FXML
	public void showAll() {
		products = productDAO.findall();
		ProductTable.setItems(FXCollections.observableList(products));
	}

	@FXML
	private void addProduct() {
		Produit newproduct = null;
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("productEdit.fxml"));
			AnchorPane addProduct = (AnchorPane) loader.load();
			String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
			addProduct.getStylesheets().add(css);
			String css2 = Main.class.getResource("CSS/productEdit.css").toExternalForm();
			addProduct.getStylesheets().add(css2);
			Stage editWindow = new Stage();
			editWindow.setTitle("Création nouveau produit");
			editWindow.initModality(Modality.WINDOW_MODAL);
			editWindow.initOwner(newProductWindow.getScene().getWindow());
			Scene scene = new Scene(addProduct);
			editWindow.setScene(scene);
			ProductEditController controller = loader.getController();
			controller.setProduct(newproduct);
			controller.setParentControlleur(this);
			editWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void editProduct() {
		Produit selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
		if (selectedProduct != null) {
			try {
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("ProductEdit.fxml"));
				AnchorPane editProduct = (AnchorPane) loader.load();
				String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
				editProduct.getStylesheets().add(css);
				String css2 = Main.class.getResource("CSS/productEdit.css").toExternalForm();
				editProduct.getStylesheets().add(css2);
				Stage editWindow = new Stage();
				editWindow.setTitle("Modification produit");
				editWindow.initModality(Modality.WINDOW_MODAL);
				editWindow.initOwner(edit.getScene().getWindow());
				Scene scene = new Scene(editProduct);
				editWindow.setScene(scene);
				ProductEditController controller = loader.getController();
				controller.setProduct(selectedProduct);
				controller.setParentControlleur(this);
				editWindow.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			CreateAlert.createAlert("WARNING", "No Selection", "Pas de produit séléctionné",
					"Merci de sélectionner un produit dans la table");
		}
	}

	@FXML
	private void delete() {
		Produit selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
		if (selectedProduct != null) {
			productDAO.delete(selectedProduct);
			refresh();
			CreateAlert.createAlert("INFORMATION", "Suppression produit", "Prdouit suprimé", null);
		} else {
			CreateAlert.createAlert("ERROR", "Empty product", "Pas de produit séléctionné",
					"Merci de sélectionner un produit dans la table.");
		}
	}

	@FXML
	private void cancel() {
		ControllerInterface ctrl = new HomeController();
		CreateScene.createNewScene("Home", quit, "home", ctrl, user);
	}

	@FXML
	public void refresh() {
		products = productDAO.findallActive();
		ProductTable.setItems(FXCollections.observableList(products));
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
