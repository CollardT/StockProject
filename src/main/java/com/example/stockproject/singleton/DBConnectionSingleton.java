package com.example.stockproject.singleton;

import java.sql.*;
public class DBConnectionSingleton {
    private String url = "jdbc:mysql://localhost:3306/grossenstock?autoReconnect=true&useSSL=false";
    private String user = "root";
    private String passwd = "";

    private static Connection conn;
    private volatile static DBConnectionSingleton single = new DBConnectionSingleton();

    private DBConnectionSingleton(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,passwd);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.println("Erreur Driver ou mdp ");
        }

    }
    public static Connection getInstance(){
        if (single==null){
            synchronized (Connection.class){
                if (single == null){
                    single = new DBConnectionSingleton();
                }
            }
        }
        return conn;
    }

}
