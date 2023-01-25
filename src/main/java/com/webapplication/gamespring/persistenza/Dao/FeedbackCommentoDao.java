package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.FeedbackCommento;

public interface FeedbackCommentoDao {

    FeedbackCommento findByPrimaryKey(String utente, int commento);
    boolean saveOrUpdate(FeedbackCommento feedbackCommento);

}
