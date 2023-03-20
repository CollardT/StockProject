package com.example.stockproject;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.factory.DAOFactory;
import com.example.stockproject.models.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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
        List<Client> c = clientDAO.findall();
        for (Client klient:
                c) {
            System.out.println("nom: "+ klient.get_nom()+"\n"+"NISS: "+klient.get_NISS());

        }


        launch();
    }
}