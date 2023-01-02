package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Wishlist;

import java.util.List;

public interface WishlistDao {
    public List<Wishlist> findAll();

    public Wishlist findByPrimaryKey(int gioco, String utente);

    public void save(Wishlist wishlist);
    public List<Wishlist> findByUser(String utente);
    public void delete(Wishlist wishlist);
    public boolean alreadyInDatabase(int gioco, String utente);
}
