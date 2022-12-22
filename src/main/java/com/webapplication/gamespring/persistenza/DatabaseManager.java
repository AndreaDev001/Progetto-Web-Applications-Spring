package com.webapplication.gamespring.persistenza;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance = null;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    private DatabaseManager() {}

    Connection connection = null;

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "postgres");
                System.out.println("Connesso");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }



}
