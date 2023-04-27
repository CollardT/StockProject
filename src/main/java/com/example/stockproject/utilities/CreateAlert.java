package com.example.stockproject.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CreateAlert {

	static public void createAlert(String type, String title, String header, String message) {
		Alert alert;
		switch (type) {
		case "INFORMATION":
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(message);
			alert.showAndWait();
			break;

		case "WARNING":
			alert = new Alert(AlertType.WARNING);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(message);
			alert.showAndWait();
			break;

		case "ERROR":
			alert = new Alert(AlertType.ERROR);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(message);
			alert.showAndWait();
			break;
		}

	}
}
