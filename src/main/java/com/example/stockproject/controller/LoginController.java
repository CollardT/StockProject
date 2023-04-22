package com.example.stockproject.controller;

import java.io.IOException;

import com.example.stockproject.Main;
import com.example.stockproject.dao.implement.UtilisateurDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateScene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController {

	// Déclaration des éléments FXML lié au controlleur
	@FXML
	private TextField login;

	@FXML
	private PasswordField password;

	@FXML
	private Button connect, quit;

	/**
	 * Vérifie si le nom de compte / password correspondent pour la connexion d'un
	 * utilisateur. Si erreur ouvre un message d'alerte
	 * 
	 * @throws IOException
	 */
	@FXML
	private void connection() throws IOException {
		UtilisateurDAO userDAO = (UtilisateurDAO) DAOFactory.getUtilisateurDao();
		String name = login.getText();
		Utilisateur user = userDAO.findByName(name);
		if (user != null) {
			if (user.get_passwordProperty().equals(password.getText())) {

				CreateScene.createNewScene("Home", connect, "home");

				FXMLLoader loader = new FXMLLoader(Main.class.getResource("Home.fxml"));
				loader.load();
				HomeController controller = loader.getController();
				controller.setUser(user);
			} else {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("ERROR");
				error.setContentText("Error Login / Password");
				error.showAndWait();
			}
		} else {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("ERROR");
			error.setContentText("Error Login / Password");
			error.showAndWait();
		}
	}

	/**
	 * Ferme l'application
	 * 
	 */
	@FXML
	private void close() {
		Stage root = (Stage) connect.getScene().getWindow();
		root.close();
	}

	/**
	 * Ecoute la touche "ENTER" et utilise la méthode connexion lorsqu'enfoncée
	 * 
	 * @param e
	 * @throws IOException
	 */
	@FXML
	private void onKeyPressed(KeyEvent e) throws IOException {
		if (e.getCode().toString() == "ENTER") {
			connection();
		}
	}
}
