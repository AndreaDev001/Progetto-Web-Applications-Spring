package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.FeedbackRecensione;

import java.sql.SQLException;

public interface FeedbackRecensioneDao {
    FeedbackRecensione findByPrimaryKey(String utente, int recensioneID) throws SQLException;
    boolean saveOrUpdate(FeedbackRecensione feedbackRecensione) throws SQLException;
}
