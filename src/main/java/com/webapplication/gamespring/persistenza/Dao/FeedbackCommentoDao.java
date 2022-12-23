package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.FeedbackCommento;
import java.util.List;

public interface FeedbackCommentoDao {
    public List<FeedbackCommento> findAll();

    public FeedbackCommento findByPrimaryKey(String utente, int commento);

    public void saveOrUpdate(FeedbackCommento utente);

    public void delete(FeedbackCommento utente);
    public boolean alreadyInDatabase(String username, Long id);
}
