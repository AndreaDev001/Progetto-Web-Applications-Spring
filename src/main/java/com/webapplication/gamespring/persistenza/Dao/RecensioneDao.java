package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.Recensione;

import java.sql.SQLException;
import java.util.List;

public interface RecensioneDao {
    List<Recensione> getGameReviews(int gameID);

    Recensione findByPrimaryKey(int id);
    Recensione getUserReview(String username, int gameID);

    int save(Recensione recensione) throws SQLException;

    void delete(int recensione);

    boolean update(Recensione recensione);

}
