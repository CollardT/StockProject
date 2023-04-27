package com.example.stockproject.controller;

import java.io.IOException;

import com.example.stockproject.dao.implement.UtilisateurDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateAlert;
import com.example.stockproject.utilities.CreateScene;

import interfaces.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements ControllerInterface {

	// Déclaration des éléments FXML lié au controlleur
	@FXML
	private TextField login;

	@FXML
	private PasswordField password;

	@FXML
	private Button connect, quit;

	private Utilisateur user;

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
				ControllerInterface ctrl = new HomeController();
				CreateScene.createNewScene("Home", connect, "home", ctrl, user);
			} else {
				CreateAlert.createAlert("ERROR", "ERROR", "Erreur de connexion", "Erreur de Login / Password");
			}
		} else {
			CreateAlert.createAlert("ERROR", "ERROR", "Erreur de connexion", "Erreur de Login / Password");
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

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
