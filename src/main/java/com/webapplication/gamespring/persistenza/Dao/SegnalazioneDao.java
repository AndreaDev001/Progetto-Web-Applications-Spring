package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Segnalazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SegnalazioneDao {
    List<Segnalazione> findAll();  // trova tutte le segnalazioni all'inteno del database

    Segnalazione findByPrimaryKey(int recensione, String utente); // effettua una ricerca per chiave primaria nel database
    Segnalazione readSegnalazione(ResultSet resultSet) throws SQLException;
    void save(Segnalazione segnalazione) throws SQLException; // salva i nuovi dati all'interno del database

    void delete(int recensione, String utente); //elimina i dati dal database
}
