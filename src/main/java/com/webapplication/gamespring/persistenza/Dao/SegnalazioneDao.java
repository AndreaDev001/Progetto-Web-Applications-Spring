package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Segnalazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SegnalazioneDao {
    public List<Segnalazione> findAll();

    public Segnalazione findByPrimaryKey(int recensione, String utente);
    public Segnalazione readSegnalazione(ResultSet resultSet) throws SQLException;
    public void saveOrUpdate(Segnalazione segnalazione);

    public void delete(int recensione, String utente);
    public boolean alreadyInDatabase(int recensione, String utente);
}
