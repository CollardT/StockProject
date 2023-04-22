package com.example.stockproject;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane root;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		String img = this.getClass().getResource("Images/logo.png").toExternalForm();
		Image icon = new Image(img);
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("STOCK EN GROS");

		initroot();

		showLogin();
	}

	/**
	 * Initialise le cadre de l'application
	 */
	public void initroot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Root.fxml"));
			root = (BorderPane) loader.load();

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lance la première fenêtre affichée pour la connexion
	 */
	public void showLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Login.fxml"));
			AnchorPane login = (AnchorPane) loader.load();

			String css = this.getClass().getResource("CSS/generalCSS.css").toExternalForm();
			String css2 = this.getClass().getResource("CSS/login.css").toExternalForm();
			login.getStylesheets().add(css);
			login.getStylesheets().add(css2);

			root.setCenter(login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}