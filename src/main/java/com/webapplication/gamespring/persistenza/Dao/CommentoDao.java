package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.model.Recensione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CommentoDao {

    List<Commento> getReviewComments(int reviewID, int startIndex, int commentsSize);
    Commento findByPrimaryKey(int id);
    Commento readCommento(ResultSet resultSet) throws SQLException;
    int save(Commento commento) throws SQLException;
    void update(Commento commento) throws SQLException;
    void delete(Commento commento) throws SQLException;

}
