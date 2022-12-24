package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.FeedbackRecensione;

import java.util.List;

public interface FeedbackRecensioneDao {
    public List<FeedbackRecensione> findAll();

    public FeedbackRecensione findByPrimaryKey(String utente, int recensione);

    public void saveOrUpdate(FeedbackRecensione feedbackRecensione);

    public void delete(FeedbackRecensione feedbackRecensione);
    public boolean alreadyInDatabase(String utente, int recensione);
}
