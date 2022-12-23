package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Recensione;

import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAll();

    public Recensione findByPrimaryKey(Long id);

    public void saveOrUpdate(Recensione recensione);

    public void delete(Recensione recensione);

    public boolean alreadyInDatabase(String utente, int id);
}
