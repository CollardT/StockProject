package com.example.stockproject.controller;

import com.example.stockproject.models.Facture;
import com.example.stockproject.models.Produit;
import com.example.stockproject.models.Utilisateur;
import javafx.fxml.FXML;


public class NewBillController {
	private Utilisateur user;
	
	public void setUser(Utilisateur user) {
        this.user = user;
           this.user.set_login(user.get_login());
    }
}
