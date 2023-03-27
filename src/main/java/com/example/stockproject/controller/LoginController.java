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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField login;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button connect, quit;
	
	@FXML
	private void connection() {
		UtilisateurDAO userDAO = (UtilisateurDAO) DAOFactory.getUtilisateurDao();
		String name = login.getText();
		Utilisateur user = userDAO.findByName(name);
		if(user != null) {
			if(user.get_password().equals(password.getText())) {
			        try {
			            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Home.fxml"));
			            AnchorPane home = (AnchorPane) loader.load();
			            Stage root = (Stage) connect.getScene().getWindow();
			            root.setScene(new Scene(home));
			            
	        	        HomeController controller = loader.getController();
	        	        controller.setUser(user);
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
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
	
	@FXML
	private void close() {
		Stage root = (Stage) connect.getScene().getWindow();
        root.close();
	}
}
