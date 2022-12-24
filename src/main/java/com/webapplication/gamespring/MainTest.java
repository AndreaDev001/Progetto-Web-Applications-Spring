package com.webapplication.gamespring;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.CommentoDao;
import com.webapplication.gamespring.persistenza.Dao.GiocoDao;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.Dao.postgres.UtenteDaoPostgres;
import com.webapplication.gamespring.persistenza.DatabaseManager;

import java.sql.Connection;

public class MainTest {
    public static void main(String[] args) {

        CommentoDao cdao = DatabaseManager.getInstance().getCommentoDao();
        Commento C = cdao.findByPrimaryKey(1);
        C.setContenuto("forzaNapoli");

        cdao.update(C);







        //DatabaseManager.getInstance().getConnection();
    }


}
