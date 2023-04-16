package com.example.stockproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.example.stockproject.HelloApplication;
import com.example.stockproject.dao.implement.ProduitDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Produit;


public class ProductsController {

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
    private Button reload, newProductWindow, edit, previous;

    private ProduitDAO productsDAO = (ProduitDAO) DAOFactory.getProduitDao();
    private List<Produit> products = productsDAO.findall();
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the Product table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue()._nomproduitPropertyProperty());
        ProductTable.setItems(FXCollections.observableList(products));        
        
     // Listen for selection changes and show the Product details when changed.
        ProductTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue));
    }
    
    /**
     * Fills all text fields to show details about the Product.
     * If the specified Product is null, all text fields are cleared.
     *
     * @param produit the Product or null
     */
    private void showProductDetails(Produit product) {
        if (product != null) {
            // Fill the labels with info from the Product object.
            nameLabel.setText(product.get_nom());
            stockLabel.setText(Integer.toString(product.get_stock()));
            isActiveCheckBox.setSelected(product.is_isActive());
        } else {
            // Product is null, remove all the text.
            nameLabel.setText("");
            stockLabel.setText("");
            isActiveCheckBox.setSelected(false);
        }
    }
    
    @FXML
    private void refresh() {
    	products = productsDAO.findall();
    	ProductTable.setItems(FXCollections.observableList(products));
    }
    
    @FXML
    private void addProduct() {
    	Produit newproduct = null;
   	 try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productEdit.fxml"));
	        AnchorPane addProduct = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage editWindow = new Stage();
	        editWindow.setTitle("Création product");
	        editWindow.initModality(Modality.WINDOW_MODAL);
	        editWindow.initOwner(newProductWindow.getScene().getWindow());
	        Scene scene = new Scene(addProduct);
	        editWindow.setScene(scene);

	        // Set the person into the controller.
	        ProductEditController controller = loader.getController();
	        controller.setProduct(newproduct);

	        // Show the dialog and wait until the user closes it
	        editWindow.showAndWait();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
    
    @FXML
    private void editProduct(){
    	Produit selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
        	 try {
        	        // Load the fxml file and create a new stage for the popup dialog.
        	        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ProductEdit.fxml"));
        	        AnchorPane editProduct = (AnchorPane) loader.load();

        	        // Create the dialog Stage.
        	        Stage editWindow = new Stage();
        	        editWindow.setTitle("Edit Product");
        	        editWindow.initModality(Modality.WINDOW_MODAL);
        	        editWindow.initOwner(edit.getScene().getWindow());
        	        Scene scene = new Scene(editProduct);
        	        editWindow.setScene(scene);

        	        // Set the Product into the controller.
        	        ProductEditController controller = loader.getController();
        	        controller.setProduct(selectedProduct);

        	        // Show the dialog and wait until the user closes it
        	        editWindow.showAndWait();
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("Pas de produit séléctionné");
            alert.setContentText("Merci de sélectionner un produit dans la table.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void cancel() {
    	try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            AnchorPane home = (AnchorPane) loader.load();
            Stage stage = (Stage) previous.getScene().getWindow();
            stage.setScene(new Scene(home));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
