package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Recensione;

import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAll();

    public Recensione findByPrimaryKey(int id);

    public void saveOrUpdate(Recensione utente);

    public void delete(Recensione utente);
}
