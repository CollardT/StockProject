package com.example.stockproject.controller;

import java.io.IOException;

import com.example.stockproject.Main;
import com.example.stockproject.dao.implement.UtilisateurDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Utilisateur;

import interfaces.ControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

				FXMLLoader loader = new FXMLLoader(Main.class.getResource("Home.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
				String css2 = Main.class.getResource("CSS/home.css").toExternalForm();
				scene.getStylesheets().add(css);
				scene.getStylesheets().add(css2);
				Stage window = (Stage) connect.getScene().getWindow();
				HomeController controller = loader.getController();
				controller.setUser(user);
				window.setScene(scene);
				window.show();

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

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
