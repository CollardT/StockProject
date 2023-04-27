package com.example.stockproject.controller;

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

public class NewUserController implements ControllerInterface {

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
	private Utilisateur user;

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
					CreateAlert.createAlert("INFORMATION", "Utilisateur créé", "Création nouvel utilisateur",
							"Le nouvel utilisateur a bien été créé");
					CreateScene.createNewScene("Home", quit, "home");
				} else {
					CreateAlert.createAlert("ERROR", "Mot de passe invalide", "Les password ne sont pas identique",
							"Merci de mettre des mots de passe identique");
				}
			} else {
				CreateAlert.createAlert("WARNING", "Mot de passe non sécurisé", "Mot de passe trop faible",
						"Votre mot de passe doit comporter une majuscule, un chiffre, un caractère spécial et être long de 6 caractère");
			}
		} else {
			CreateAlert.createAlert("ERROR", "Creation utilisateur error", "échec de la création d'utilisateur",
					"Rééssayer en changeant les paramètres");
		}

	}

	/**
	 * Ferme la fenêtre de création d'utilisateur
	 * 
	 */
	@FXML
	private void close() {
		ControllerInterface ctrl = new HomeController();
		CreateScene.createNewScene("Home", quit, "home", ctrl, user);
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
