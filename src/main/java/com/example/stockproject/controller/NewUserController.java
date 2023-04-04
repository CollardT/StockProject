package com.example.stockproject.controller;

import java.io.IOException;

import com.example.stockproject.HelloApplication;
import com.example.stockproject.dao.implement.UtilisateurDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Utilisateur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewUserController {
	
	@FXML
	private TextField login;
	@FXML
	private PasswordField password, confirmPassword;
	@FXML
	private Button submit, cancel;
	
	private UtilisateurDAO userDAO = (UtilisateurDAO) DAOFactory.getUtilisateurDao();
	private String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$";
	
	@FXML
	private void confirm() {
		Utilisateur user = new Utilisateur();
		if(userDAO.findByName(login.getText()) == null) {
			if(password.getText().matches(passwordRegex)) {
				if(password.getText().equals(confirmPassword.getText())) {
					user.set_login(login.getText());
					user.set_password(password.getText());
					userDAO.create(user);
					Stage root = (Stage) submit.getScene().getWindow();
					root.close();
				} else {
					// Nothing selected.
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Invalid Password");
					alert.setHeaderText("Les password ne sont pas identique");
					alert.setContentText("Merci de mettre des mots de passe identique.");
					alert.showAndWait();
				}
			} else {
				// Nothing selected.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Mot de passe non sécurisé");
	            alert.setHeaderText("Mot de passe trop faible");
	            alert.setContentText("Votre mot de passe doit comporter une majuscule, un chiffre, un caractère spécial et être long de 6 caractère");
	            alert.showAndWait();
			}
		} else {
			// Nothing selected.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Creation utilisateur error");
            alert.setHeaderText("échec de la création d'utilisateur");
            alert.setContentText("Rééssayer en changeant les paramètres");
            alert.showAndWait();
		}
		
	}
	
	@FXML
	private void close() {
		try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
            AnchorPane home = (AnchorPane) loader.load();
            Stage root = (Stage) cancel.getScene().getWindow();
            root.setScene(new Scene(home));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
