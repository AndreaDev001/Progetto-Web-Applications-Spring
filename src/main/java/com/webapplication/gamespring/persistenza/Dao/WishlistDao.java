package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface WishlistDao {
    List<Wishlist> findAll();  // trova tutte le wishlist all'inteno del database
    Wishlist findByPrimaryKey(int gioco, String utente); // effettua una ricerca per chiave primaria nel database
    Wishlist readWishlist(ResultSet resultSet) throws SQLException;
    void save(Wishlist wishlist); // salva i nuovi dati all'interno del database
    List<Wishlist> findByUser(String utente);
    void delete(Wishlist wishlist); //elimina i dati dal database
    boolean alreadyInDatabase(int gioco, String utente); // controlla se esiste già all'interno del database
}
