package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.FeedbackCommento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface FeedbackCommentoDao {
    public List<FeedbackCommento> findAll();

    public FeedbackCommento findByPrimaryKey(String utente, int commento);
    public FeedbackCommento readFeedbackCommento(ResultSet resultSet) throws SQLException;

    public boolean saveOrUpdate(FeedbackCommento feedbackCommento);

    public boolean delete(FeedbackCommento feedbackCommento);
    public boolean alreadyInDatabase(String utente, int commento);


}
