package com.webapplication.gamespring;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.Dao.postgres.UtenteDaoPostgres;
import com.webapplication.gamespring.persistenza.DatabaseManager;

import java.sql.Connection;

public class MainTest {
    public static void main(String[] args) {

/*
        Utente U = new Utente();
        U.setUsername("Pie_Ox");
        U.setPassword("123456");
        U.setEmail("piero.stalteri@icloud.com");
        U.setBandito(false);
        U.setAmministratore(true);


        UtenteDao udao = DatabaseManager.getInstance().getUtenteDao();
        udao.saveOrUpdate(U);

*/

        DatabaseManager.getInstance().getConnection();
    }


}
