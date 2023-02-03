package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Recensione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RecensioneDao {
    List<Recensione> getGameReviews(int gameID);  // trova tutte le recensioni di un gioco all'inteno del database

    Recensione findByPrimaryKey(int id); // effettua una ricerca per chiave primaria nel database
    Recensione getUserReview(String username, int gameID); // permette di ottenere la recensione specifica di un utente su un gioco
    List<Recensione> getUserReviews(String username); // permette di ottenere tutte le recensioni di un utente

    Recensione readRecensione(ResultSet resultSet) throws SQLException; // permette di leggere la recensione per evitare duplicazione del codice

    int save(Recensione recensione) throws SQLException; // salva i nuovi dati all'interno del database

    void delete(int recensione); //elimina i dati dal database

    boolean update(Recensione recensione) throws SQLException; // aggiorna i dati all'interno del database

}
