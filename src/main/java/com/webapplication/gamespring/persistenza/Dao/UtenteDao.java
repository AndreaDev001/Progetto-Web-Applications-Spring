package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Utente;

import java.util.List;

public interface UtenteDao {
    public List<Utente> findAll();

    public Utente findByPrimaryKey(String username);

    public void saveOrUpdate(Utente utente);

    public void delete(Utente utente);

    public boolean alreadyInDatabase(String username);
}
