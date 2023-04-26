package com.example.stockproject.utilities;

import java.io.IOException;

import com.example.stockproject.Main;
import com.example.stockproject.models.Utilisateur;

import interfaces.ControllerInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateScene {

	/**
	 * Fonction permettant la création d'une nouvelle scène (changement de pages sur
	 * l'application)
	 *
	 * @param view   Nom du fichier .fxml lié à la nouvelle page
	 * 
	 * @param button ID du bouton sur lequel on clique afin de pour récupérer le
	 *               parent et changer la scène
	 */
	static public void createNewScene(String view, Node button) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(view + ".fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage window = (Stage) button.getScene().getWindow();
			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Fonction permettant la création d'une nouvelle scène (changement de pages sur
	 * l'application)
	 * 
	 * @param view         Nom du fichier .fxml lié à la nouvelle page
	 * 
	 * @param button       ID du bouton sur lequel on clique afin de pour récupérer
	 *                     le parent et changer la scène
	 * 
	 * @param optionnalCss Nom du fichier CSS lié à la nouvelle page
	 * 
	 */
	static public void createNewScene(String view, Node button, String optionnalCss) {
		try {

			FXMLLoader loader = new FXMLLoader(Main.class.getResource(view + ".fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
			String css2 = Main.class.getResource("CSS/" + optionnalCss + ".css").toExternalForm();
			scene.getStylesheets().add(css);
			scene.getStylesheets().add(css2);
			Stage window = (Stage) button.getScene().getWindow();
			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Fonction permettant la création d'une nouvelle scène (changement de pages sur
	 * l'application)
	 * 
	 * @param view         Nom du fichier .fxml lié à la nouvelle page
	 * 
	 * @param button       ID du bouton sur lequel on clique afin de pour récupérer
	 *                     le parent et changer la scène
	 * 
	 * @param optionnalCss Nom du fichier CSS lié à la nouvelle page
	 * 
	 */
	static public void createNewScene(String view, Node button, String optionnalCss, ControllerInterface ctrl,
			Utilisateur user) {
		try {

			FXMLLoader loader = new FXMLLoader(Main.class.getResource(view + ".fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			String css = Main.class.getResource("CSS/generalCSS.css").toExternalForm();
			String css2 = Main.class.getResource("CSS/" + optionnalCss + ".css").toExternalForm();
			scene.getStylesheets().add(css);
			scene.getStylesheets().add(css2);
			Stage window = (Stage) button.getScene().getWindow();
			ControllerInterface controller = ctrl;
			controller = loader.getController();
			controller.setUser(user);
			window.setScene(scene);
			window.show();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
