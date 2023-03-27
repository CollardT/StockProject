package com.example.stockproject.controller;

import java.io.IOException;

import com.example.stockproject.HelloApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController {
	@FXML
	private Button clients, products, bills, disconnecting;
	
	@FXML
	private void openClientsList() {
		try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Clients.fxml"));
            AnchorPane clientsList = (AnchorPane) loader.load();
            Stage root = (Stage) clients.getScene().getWindow();
            root.setScene(new Scene(clientsList));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void openProductsList() {
		try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Products.fxml"));
            AnchorPane productsList = (AnchorPane) loader.load();
            Stage root = (Stage) products.getScene().getWindow();
            root.setScene(new Scene(productsList));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void openBillsList() {
		try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Bills.fxml"));
            AnchorPane billsList = (AnchorPane) loader.load();
            Stage root = (Stage) bills.getScene().getWindow();
            root.setScene(new Scene(billsList));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	private void disconnect() {
		try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();
            Stage root = (Stage) disconnecting.getScene().getWindow();
            root.setScene(new Scene(login));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
