package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.FeedbackRecensione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface FeedbackRecensioneDao {
    public List<FeedbackRecensione> findAll();

    public FeedbackRecensione findByPrimaryKey(String utente, int recensione);
    public FeedbackRecensione readFeedbackRecensione(ResultSet resultSet) throws SQLException;

    public void saveOrUpdate(FeedbackRecensione feedbackRecensione);

    public void delete(FeedbackRecensione feedbackRecensione);
    public boolean alreadyInDatabase(String utente, int recensione);
}
