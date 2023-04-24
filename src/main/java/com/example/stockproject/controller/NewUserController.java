package com.example.stockproject.controller;

import com.example.stockproject.dao.implement.UtilisateurDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Utilisateur;
import com.example.stockproject.utilities.CreateScene;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NewUserController {

	// Déclaration des éléments FXML lié au controlleur
	@FXML
	private TextField login;
	@FXML
	private PasswordField password, confirmPassword;
	@FXML
	private Button submit, quit;

	private UtilisateurDAO userDAO = (UtilisateurDAO) DAOFactory.getUtilisateurDao();

	// Regex de vérification mail
	private String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$";

	/**
	 * Créé un nouvel utilisateur après avoir vérifier si les mots de passes
	 * correspondent. Sinon affiche une alerte
	 * 
	 */
	@FXML
	private void confirm() {
		Utilisateur user = new Utilisateur();
		if (userDAO.findByName(login.getText()) == null) {
			if (password.getText().matches(passwordRegex)) {
				if (password.getText().equals(confirmPassword.getText())) {
					user.set_login(login.getText());
					user.set_password(password.getText());
					userDAO.create(user);
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("User create");
					alert.setHeaderText("Création nouvel utilisateur");
					alert.setContentText("Le nouvel utilisateur a bien été créé");
					alert.showAndWait();
					CreateScene.createNewScene("Home", quit, "home");
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Password");
					alert.setHeaderText("Les password ne sont pas identique");
					alert.setContentText("Merci de mettre des mots de passe identique.");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Mot de passe non sécurisé");
				alert.setHeaderText("Mot de passe trop faible");
				alert.setContentText(
						"Votre mot de passe doit comporter une majuscule, un chiffre, un caractère spécial et être long de 6 caractère");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Creation utilisateur error");
			alert.setHeaderText("échec de la création d'utilisateur");
			alert.setContentText("Rééssayer en changeant les paramètres");
			alert.showAndWait();
		}

	}

	/**
	 * Ferme la fenêtre de création d'utilisateur
	 * 
	 */
	@FXML
	private void close() {
//		try {
//            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Home.fxml"));
//            AnchorPane home = (AnchorPane) loader.load();
//            Stage root = (Stage) cancel.getScene().getWindow();
//            root.setScene(new Scene(home));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
		CreateScene.createNewScene("Home", quit, "home");
	}
}
