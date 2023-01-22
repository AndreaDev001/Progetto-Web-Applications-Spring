package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UtenteDao {
    public List<Utente> findAll();
    public Utente findByPrimaryKey(String username);
    public Utente readUtente(ResultSet resultSet) throws SQLException;
    public void saveOrUpdate(Utente utente);

    public void delete(String username);

    public List<Utente> fuzzySearch(String username);

    public boolean alreadyInDatabase(String username);
}
