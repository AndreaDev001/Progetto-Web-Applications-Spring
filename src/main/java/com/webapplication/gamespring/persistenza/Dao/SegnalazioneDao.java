package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Segnalazione;

import java.util.List;

public interface SegnalazioneDao {
    public List<Segnalazione> findAll();

    public Segnalazione findByPrimaryKey(int recensione, String utente);

    public void saveOrUpdate(Segnalazione utente);

    public void delete(Segnalazione utente);
    public boolean alreadyInDatabase(Long id, String username);
}
