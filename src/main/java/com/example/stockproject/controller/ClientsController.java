package com.example.stockproject.controller;

import java.io.IOException;
import java.util.List;

import com.example.stockproject.Main;
import com.example.stockproject.dao.implement.ClientDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Client;
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

public class ClientsController implements ControllerInterface {

	// Déclaration des éléments FXML lié au controlleur
	@FXML
	private TableView<Client> ClientTable;
	@FXML
	private TableColumn<Client, String> nameColumn;
	@FXML
	private Label nameLabel, nissLabel, emailLabel, adresseLabel;
	@FXML
	private CheckBox isActiveCheckBox;
	@FXML
	private Button newClientWindow, edit, quit;

	private ClientDAO clientDAO = (ClientDAO) DAOFactory.getClientDao();
	private List<Client> clients = clientDAO.findallactive();
	private Utilisateur user;

	/**
	 * Initialise le controlleur et le tableau de clients
	 */
	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		ClientTable.setItems(FXCollections.observableList(clients));
		ClientTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showClientDetails(newValue));
	}

	/**
	 * Récupère les informations de la personne sélectionnée ou remet les champs
	 * vides si personne n'est sélectionné
	 *
	 * @param person
	 */
	private void showClientDetails(Client client) {
		if (client != null) {
			nameLabel.setText(client.get_nom());
			nissLabel.setText(client.get_NISS());
			emailLabel.setText(client.get_email());
			adresseLabel.setText(client.get_adresse());
			isActiveCheckBox.setSelected(client.get_isActive());
		} else {
			nameLabel.setText("");
			nissLabel.setText("");
			emailLabel.setText("");
			adresseLabel.setText("");
			isActiveCheckBox.setSelected(false);
		}
	}

	@FXML
	public void showActiv() {
		refresh();
	}

	@FXML
	public void showAll() {
		clients = clientDAO.findall();
		ClientTable.setItems(FXCollections.observableList(clients));
	}

	/**
	 * Ouvre la fenêtre pour créer un nouveau client
	 */
	@FXML
	private void addClient() {
		Client newClient = null;
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("ClientEdit.fxml"));
			AnchorPane addClient = (AnchorPane) loader.load();
			String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
			addClient.getStylesheets().add(css);
			String css2 = Main.class.getResource("CSS/clientEdit.css").toExternalForm();
			addClient.getStylesheets().add(css2);
			Stage editWindow = new Stage();
			editWindow.setTitle("Création Client");
			editWindow.initModality(Modality.WINDOW_MODAL);
			editWindow.initOwner(newClientWindow.getScene().getWindow());
			Scene scene = new Scene(addClient);
			editWindow.setScene(scene);
			ClientEditController controller = loader.getController();
			controller.setClient(newClient);
			controller.setParentControlleur(this);

			editWindow.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ouvre la fenêtre pour éditer un client Si aucun client n'es sélectionné
	 * renvoie une alerte d'erreur
	 */
	@FXML
	private void editClient() {
		Client selectedPerson = ClientTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			try {
				FXMLLoader loader = new FXMLLoader(Main.class.getResource("ClientEdit.fxml"));
				AnchorPane editClient = (AnchorPane) loader.load();
				String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
				editClient.getStylesheets().add(css);
				String css2 = Main.class.getResource("CSS/clientEdit.css").toExternalForm();
				editClient.getStylesheets().add(css2);
				Stage editWindow = new Stage();
				editWindow.setTitle("Edit Client");
				editWindow.initModality(Modality.WINDOW_MODAL);
				editWindow.initOwner(edit.getScene().getWindow());
				Scene scene = new Scene(editClient);
				editWindow.setScene(scene);
				ClientEditController controller = loader.getController();
				controller.setClient(selectedPerson);
				controller.setParentControlleur(this);
				editWindow.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			CreateAlert.createAlert("ERROR", "Empty client", "Pas de client séléctionné",
					"Merci de sélectionner un client dans la table.");
		}
	}

	@FXML
	private void delete() {
		Client selectedPerson = ClientTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			clientDAO.delete(selectedPerson);
			refresh();
			CreateAlert.createAlert("INFORMATION", "Suppression client", "Client suprimé", null);
		} else {
			CreateAlert.createAlert("ERROR", "Empty client", "Pas de client séléctionné",
					"Merci de sélectionner un client dans la table.");
		}
	}

	/*
	 * Refresh la liste des clients
	 */
	@FXML
	public void refresh() {
		clients = clientDAO.findallactive();
		ClientTable.setItems(FXCollections.observableList(clients));
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
