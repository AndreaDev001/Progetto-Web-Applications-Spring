package com.webapplication.gamespring.persistenza;


import com.webapplication.gamespring.persistenza.Dao.*;
import com.webapplication.gamespring.persistenza.Dao.postgres.*;

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
    public CommentoDao getCommentoDao(){return new CommentoDaoPostgres(getConnection());}
    public WishlistDao getWishlistDao(){return new WishlistDaoPostgres(getConnection());}
    public SegnalazioneDao getSegnalazioneDao(){return new SegnalazioneDaoPostgres(getConnection());}
    public FeedbackCommentoDao getFeedbackCommentoDao(){ return new FeedbackCommentoDaoPostgres(getConnection());}
    public FeedbackRecensioneDao getFeedbackRecensioneDao(){ return new FeedbackRecensioneDaoPostgres(getConnection());}

}
