package com.webapplication.gamespring.persistenza;


import com.webapplication.gamespring.persistenza.Dao.GiocoDao;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.Dao.postgres.GiocoDaoPostgres;
import com.webapplication.gamespring.persistenza.Dao.postgres.RecensioneDaoPostgres;
import com.webapplication.gamespring.persistenza.Dao.postgres.UtenteDaoPostgres;

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
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
                System.out.println("Connesso");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public UtenteDao getUtenteDao() {
        return new UtenteDaoPostgres(getConnection());
    }
    public RecensioneDao getRecensioneDao(){return new RecensioneDaoPostgres(getConnection());}
    public GiocoDao getGiocoDao(){return new GiocoDaoPostgres(getConnection());}

}
