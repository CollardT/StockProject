package com.example.stockproject.controller;

import com.example.stockproject.dao.implement.ClientDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientEditController {
	@FXML
    private TextField nameLabel;
    @FXML
    private TextField nissLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private TextField adresseLabel;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Button confirm, cancel;
    
    private String nomRegex = "[a-zA-Z ']*";
    private String NISSRegex = "[0-9]{11}";
    private String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";


    private Client client;
    private ClientDAO clientDAO = (ClientDAO) DAOFactory.getClientDao();

    /**
     * Sets the client to be edited in the dialog.
     *
     * @param client
     */
    public void setClient(Client client) {
    	this.client = client;
    	if(client != null) { 		
    		nameLabel.setText(this.client.get_nom());
    		nissLabel.setText(this.client.get_NISS());
    		emailLabel.setText(this.client.get_email());
    		adresseLabel.setText(this.client.get_adresse());
    		isActiveCheckBox.setSelected(this.client.get_isActive());
    	} else {
    		this.client = new Client();
            nameLabel.setText("");
            nissLabel.setText("");
            emailLabel.setText("");
            adresseLabel.setText("");
            isActiveCheckBox.setSelected(true);
    	}
    }

    @FXML
    private void submit() {
    	if(isInputValid()) {
    		this.client.set_nom(nameLabel.getText());
    		this.client.set_NISS(nissLabel.getText());
    		this.client.set_email(emailLabel.getText());
    		this.client.set_adresse(adresseLabel.getText());
    		this.client.set_isActive(isActiveCheckBox.isSelected());
    		
    		if(clientDAO.find(this.client.get_idClient()) != null) {
    			clientDAO.update(this.client);
    		} else {
    			clientDAO.create(this.client);
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

        if (nameLabel.getText() == null || !nameLabel.getText().matches(nomRegex)) {
            errorMessage += "Nom du client invalide\n";
        }
        if (nissLabel.getText() == null || !nissLabel.getText().matches(NISSRegex)) {
            errorMessage += "Le numéro national doit uniquement être composé de 11 chiffres\n";
        }
        if (emailLabel.getText() == null || !emailLabel.getText().matches(emailRegex)) {
            errorMessage += "Adresse mail invalide\n";
        }

        if (adresseLabel.getText() == null || adresseLabel.getText().length() == 0) {
            errorMessage += "L'adresse est invalide\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            //alert.initOwner(editWindow);
            alert.setTitle("Erreur d'encodage");
            alert.setHeaderText("Merci de vérifier les champs invalides");
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
