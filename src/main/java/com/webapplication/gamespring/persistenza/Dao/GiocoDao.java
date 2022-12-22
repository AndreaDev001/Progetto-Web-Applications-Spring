package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Gioco;

import java.util.List;

public interface GiocoDao {

    public List<Gioco> findAll();

    public Gioco findByPrimaryKey(int id);

    public void saveOrUpdate(Gioco utente);

    public void delete(Gioco utente);
}
