package com.example.stockproject.controller;

import com.example.stockproject.dao.implement.ProduitDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Produit;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductEditController {
	@FXML
    private TextField nameLabel;
    @FXML
    private TextField stockLabel;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Button confirm, cancel;
    
    private Produit product;
    private ProduitDAO productDAO = (ProduitDAO) DAOFactory.getProduitDao();
    
    /**
     * Sets the product to be edited in the dialog.
     *
     * @param product
     */
    public void setProduct(Produit product) {
        this.product = product;
        if(product != null) {
            nameLabel.setText(this.product.get_nom());
            stockLabel.setText(Integer.toString(this.product.get_stock()));
            isActiveCheckBox.setSelected(this.product.get_isActive());
        } else {
        	this.product = new Produit();
        	nameLabel.setText("");
        	stockLabel.setText("");
        	isActiveCheckBox.setSelected(true);
        }
    }

    @FXML
    private void submit() {
    	if(isInputValid()) {
    		this.product.set_nom(nameLabel.getText());
    		this.product.set_stock(Integer.parseInt(stockLabel.getText()));
    		this.product.set_isActive(isActiveCheckBox.isSelected());
    		
    		if(productDAO.find(this.product.get_idproduit()) != null) {
    			productDAO.update(this.product);
    		} else {
    			productDAO.create(this.product);
    		}
    		Stage thisWindow = (Stage) confirm.getScene().getWindow();
    		thisWindow.close();
    		
    	}
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameLabel.getText() == null || nameLabel.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (stockLabel.getText() == null || stockLabel.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            //alert.initOwner(editWindow);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
    @FXML
    private void cancel() {
		Stage thisWindow = (Stage) cancel.getScene().getWindow();
		thisWindow.close();
    }
}
