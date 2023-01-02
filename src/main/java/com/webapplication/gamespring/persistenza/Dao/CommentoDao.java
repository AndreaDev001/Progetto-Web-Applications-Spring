package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.Recensione;

import java.util.List;

public interface CommentoDao {

    public List<Commento> findAll();
    public Commento findByPrimaryKey(int id);

    public void save(Commento commento);
    public void update(Commento commento);



    public void delete(Commento commento);
}
