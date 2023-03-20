package com.example.stockproject.factory;

import com.example.stockproject.dao.DAO;
import com.example.stockproject.dao.implement.ClientDAO;
import com.example.stockproject.models.Client;
import com.example.stockproject.singleton.DBConnectionSingleton;

import java.sql.Connection;

public class DAOFactory {
    protected static final Connection conn = DBConnectionSingleton.getInstance();

    public static DAO<Client> getClientDao(){
        return new ClientDAO(conn);
    }

}