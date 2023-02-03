package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Gioco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GiocoDao {

    List<Gioco> findAll();  // trova tutti i giochi all'inteno del database

    Gioco findByPrimaryKey(int id); // effettua una ricerca per chiave primaria nel database
    Gioco readGioco(ResultSet resultSet) throws SQLException; // permette di leggere il gioco per evitare duplicazione del codice

    void saveOrUpdate(Gioco gioco); // aggiorna o salva i dati all'interno del database

    void delete(Gioco gioco); //elimina i dati dal database

    boolean alreadyInDatabase(int id); // controlla se esiste già all'interno del database
}
