package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.model.Recensione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CommentoDao {

    List<Commento> getReviewComments(int reviewID, int startIndex, int commentsSize);
    Commento readCommento(ResultSet resultSet) throws SQLException; // permette di leggere il commento per evitare duplicazione del codice
    int save(Commento commento) throws SQLException; // salva i nuovi dati all'interno del database
    void update(Commento commento) throws SQLException; // aggiorna i dati all'interno del database
    void delete(Commento commento) throws SQLException; //elimina i dati dal database

}
