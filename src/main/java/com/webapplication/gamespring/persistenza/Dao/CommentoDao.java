package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Commento;

import java.util.List;

public interface CommentoDao {

    public List<Commento> findAll();

    public Commento findByPrimaryKey(int id);

    public void saveOrUpdate(Commento utente);

    public void delete(Commento utente);
    public boolean alreadyInDatabase(Long id);
}
