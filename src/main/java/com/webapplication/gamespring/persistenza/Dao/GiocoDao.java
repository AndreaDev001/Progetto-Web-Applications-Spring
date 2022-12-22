package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Gioco;

import java.util.List;

public interface GiocoDao {

    public List<Gioco> findAll();

    public Gioco findByPrimaryKey(Long id);

    public void saveOrUpdate(Gioco gioco);

    public void delete(Gioco gioco);

    public boolean alreadyInDatabase(Long id);
}
