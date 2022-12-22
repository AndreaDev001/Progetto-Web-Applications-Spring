package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Whishlist;

import java.util.List;

public interface WhishlistDao {
    public List<Whishlist> findAll();

    public Whishlist findByPrimaryKey(int gioco, String utente);

    public void saveOrUpdate(Whishlist utente);

    public void delete(Whishlist utente);
}
