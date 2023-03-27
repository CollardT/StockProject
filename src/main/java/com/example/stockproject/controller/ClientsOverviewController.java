package com.example.stockproject.controller;

import javafx.collections.FXCollections;
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
import com.example.stockproject.dao.implement.ClientDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Client;

public class ClientsOverviewController {
    @FXML
    private TableView<Client> ClientTable;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label nissLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Button reload, newClientWindow, edit, previous;

    private ClientDAO clientsDAO = (ClientDAO) DAOFactory.getClientDao();
    private List<Client> clients = clientsDAO.findall();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the Client table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        ClientTable.setItems(FXCollections.observableList(clients));        
        
     // Listen for selection changes and show the person details when changed.
        ClientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClientDetails(newValue));
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showClientDetails(Client client) {
        if (client != null) {
            // Fill the labels with info from the person object.
            nameLabel.setText(client.get_nom());
            nissLabel.setText(client.get_NISS());
            emailLabel.setText(client.get_email());
            adresseLabel.setText(client.get_adresse());
            isActiveCheckBox.setSelected(client.get_isActive());
        } else {
            // Person is null, remove all the text.
            nameLabel.setText("");
            nissLabel.setText("");
            emailLabel.setText("");
            adresseLabel.setText("");
            isActiveCheckBox.setSelected(false);
        }
    }
    
    @FXML
    private void refresh() {
    	clients = clientsDAO.findall();
    	ClientTable.setItems(FXCollections.observableList(clients));
    }
    
    @FXML
    private void addClient() {
    	Client newClient = null;
   	 try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ClientEdit.fxml"));
	        AnchorPane addClient = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage editWindow = new Stage();
	        editWindow.setTitle("Création Client");
	        editWindow.initModality(Modality.WINDOW_MODAL);
	        editWindow.initOwner(newClientWindow.getScene().getWindow());
	        Scene scene = new Scene(addClient);
	        editWindow.setScene(scene);

	        // Set the person into the controller.
	        ClientEditController controller = loader.getController();
	        controller.setClient(newClient);

	        // Show the dialog and wait until the user closes it
	        editWindow.showAndWait();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
    
    @FXML
    private void editClient(){
    	Client selectedPerson = ClientTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
        	 try {
        	        // Load the fxml file and create a new stage for the popup dialog.
        	        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ClientEdit.fxml"));
        	        AnchorPane editClient = (AnchorPane) loader.load();

        	        // Create the dialog Stage.
        	        Stage editWindow = new Stage();
        	        editWindow.setTitle("Edit Client");
        	        editWindow.initModality(Modality.WINDOW_MODAL);
        	        editWindow.initOwner(edit.getScene().getWindow());
        	        Scene scene = new Scene(editClient);
        	        editWindow.setScene(scene);

        	        // Set the person into the controller.
        	        ClientEditController controller = loader.getController();
        	        controller.setClient(selectedPerson);

        	        // Show the dialog and wait until the user closes it
        	        editWindow.showAndWait();
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("Pas de client séléctionné");
            alert.setContentText("Merci de sélectionner un client dans la table.");
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

