package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Recensione;

import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAll();

    public Recensione findByPrimaryKey(int id);

    public int save(Recensione recensione);

    public void delete(Recensione recensione);

    public boolean update(Recensione recensione);

}
