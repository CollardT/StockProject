package com.example.stockproject.controller;

import com.example.stockproject.dao.implement.ProduitDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Produit;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateAlert;
import com.example.stockproject.utilities.IsANumber;

import interfaces.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductEditController implements ControllerInterface {
	@FXML
	private TextField nameLabel;
	@FXML
	private TextField stockLabel;
	@FXML
	private CheckBox isActiveCheckBox;
	@FXML
	private Button confirm, quit;

	private Produit product;
	private ProduitDAO productDAO = (ProduitDAO) DAOFactory.getProduitDao();
	private Utilisateur user;

	/**
	 * Sets the product to be edited in the dialog.
	 *
	 * @param product
	 */
	public void setProduct(Produit product) {
		this.product = product;
		if (product != null) {
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
		if (isInputValid()) {
			this.product.set_nom(nameLabel.getText());
			this.product.set_stock(Integer.parseInt(stockLabel.getText()));
			this.product.set_isActive(isActiveCheckBox.isSelected());

			if (productDAO.find(this.product.get_idProduit()) != null) {
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
			errorMessage += "Nom du produit invalide\n";
		}
		if (stockLabel.getText() == null || !IsANumber.isANumber(stockLabel.getText())) {
			errorMessage += "Veuillez n'entrer que des chiffres pour le stock\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			CreateAlert.createAlert("ERROR", "Champs Invalide", "Corrigez les champs invalides", errorMessage);
			return false;
		}
	}

	@FXML
	private void cancel() {
		Stage thisWindow = (Stage) quit.getScene().getWindow();
		thisWindow.close();
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
