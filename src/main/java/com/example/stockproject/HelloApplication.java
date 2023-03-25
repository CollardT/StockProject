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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DAO<Client> clientDAO = DAOFactory.getClientDao();
        DAO<Facture> factureDAO = DAOFactory.getFactureDao();
        List<Client> c = clientDAO.findall();
        for (Client klient:
                c) {
            System.out.println("nom: "+ klient.get_nom()+"\n"+"NISS: "+klient.get_NISS());

        }
        Client d= clientDAO.find(3);

        List<Facture> factures = factureDAO.findall();
        for (Facture facture:
                factures) {
            System.out.println(facture.get_idFacture());
            for (Map.Entry<Produit,Integer> set :
                    facture.get_produitsvendus().entrySet()
            ) {
                System.out.println(set.getKey().get_nom()+"===="+set.getValue());
            }
        }
        factureDAO.create(factures.get(0));


        launch();
    }
}