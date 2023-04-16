package com.example.stockproject;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.dao.implement.FactureDAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Client;
import com.example.stockproject.models.Facture;
import com.example.stockproject.models.Produit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HelloApplication extends Application {
	
	private Stage primaryStage;
    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Stock en gros");

        initroot();

        showLogin();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initroot() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("Root.fxml"));
            root = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showLogin() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("Login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            root.setCenter(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

//    public static void main(String[] args) {
//        DAO<Client> clientDAO = DAOFactory.getClientDao();
//        DAO<Facture> factureDAO = DAOFactory.getFactureDao();
//        List<Client> c = clientDAO.findall();
//        for (Client klient:
//                c) {
//            System.out.println("nom: "+ klient.get_nom()+"\n"+"NISS: "+klient.get_NISS());
//
//        }
//        Client d= clientDAO.find(3);
//
//        List<Facture> factures = factureDAO.findall();
//        for (Facture facture:
//                factures) {
//            System.out.println(facture.get_idFacture());
//            for (Map.Entry<Produit,Integer> set :
//                    facture.get_produitsvendus().entrySet()
//            ) {
//                System.out.println(set.getKey().get_nom()+"===="+set.getValue());
//            }
//        }
//
//
//
//        launch();
//    }
}