package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UtenteDao {
    List<Utente> findAll();  // trova tutti gli utenti all'inteno del database
    Utente findByPrimaryKey(String username); // effettua una ricerca per chiave primaria nel database
    Utente readUtente(ResultSet resultSet) throws SQLException; // permette di leggere l'utente per evitare duplicazione del codice
    void saveOrUpdate(Utente utente);  // partendo dalla classe utente salva o aggiorna il contenuto della classe nel database

    void delete(String username); //elimina i dati dal database

    List<Utente> fuzzySearch(String username); //effettua una ricerca dinamnica all'inteno del database

    boolean alreadyInDatabase(String username); // controlla se esiste già all'interno del database
}
