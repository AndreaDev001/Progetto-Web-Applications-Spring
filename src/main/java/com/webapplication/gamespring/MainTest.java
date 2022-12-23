package com.webapplication.gamespring;

import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.GiocoDao;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.Dao.postgres.UtenteDaoPostgres;
import com.webapplication.gamespring.persistenza.DatabaseManager;

import java.sql.Connection;

public class MainTest {
    public static void main(String[] args) {

        Recensione R = new Recensione();
        R.setTitolo("aaaaa");
        R.setContenuto("Bellissimoooo");
        R.setVoto(100);
        R.setNumeroMiPiace(50);
        R.setNumeroNonMiPiace(20);
        R.setGioco(11);
        R.setUtente("Pie_Oxx");
        R.setId(2L);


        RecensioneDao rdao = DatabaseManager.getInstance().getRecensioneDao();
        rdao.saveOrUpdate(R);







        //DatabaseManager.getInstance().getConnection();
    }


}
