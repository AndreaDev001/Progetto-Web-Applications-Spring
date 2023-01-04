package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.model.Recensione;

import java.sql.SQLException;
import java.util.List;

public interface CommentoDao {

    public List<Commento> findAll();
    List<Commento> getReviewComments(int reviewID, int startIndex, int commentsSize);

    public Commento findByPrimaryKey(int id);

    public int save(Commento commento) throws SQLException;
    public void update(Commento commento) throws SQLException;

    public void delete(Commento commento) throws SQLException;

}
