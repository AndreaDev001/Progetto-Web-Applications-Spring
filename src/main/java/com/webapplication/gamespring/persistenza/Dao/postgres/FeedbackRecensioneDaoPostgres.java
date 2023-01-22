package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.model.FeedbackRecensione;
import com.webapplication.gamespring.persistenza.Dao.FeedbackRecensioneDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRecensioneDaoPostgres implements FeedbackRecensioneDao {
    Connection connection;
    public FeedbackRecensioneDaoPostgres(Connection connection){this.connection = connection;}


    @Override
    public List<FeedbackRecensione> findAll() {
        List<FeedbackRecensione> feedbackRecensioni = new ArrayList<FeedbackRecensione>();
        String query = "select * from DatabaseProg.feedback_recensione";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                feedbackRecensioni.add(readFeedbackRecensione(rs));
        } catch (SQLException exception){
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return feedbackRecensioni;
    }
    @Override
    public FeedbackRecensione findByPrimaryKey(String utente, int recensione) {
        String query = "select * from DatabaseProg.feedback_recensione where utente = ? and recensione = ?";
        try
        {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return readFeedbackRecensione(rs);

        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return null;
    }
    @Override
    public FeedbackRecensione readFeedbackRecensione(ResultSet resultSet) throws SQLException {
        String utente = resultSet.getString("utente");
        int recensione = resultSet.getInt("recensione");
        boolean tipo = resultSet.getBoolean("tipo");
        return new FeedbackRecensione(utente,recensione,tipo);
    }
    @Override
    public void saveOrUpdate(FeedbackRecensione feedbackRecensione) {
        if (!alreadyInDatabase(feedbackRecensione.getUtente(), feedbackRecensione.getRecensione())) {
            String insertStr = "INSERT INTO DatabaseProg.feedback_recensione VALUES (?, ?, ?)";
            PreparedStatement st;
            try
            {
                st = connection.prepareStatement(insertStr);
                st.setString(1, feedbackRecensione.getUtente());
                st.setInt(2, feedbackRecensione.getRecensione());
                st.setBoolean(3, feedbackRecensione.isTipo());
                st.executeUpdate();

            } catch (SQLException exception) {
                // TODO Auto-generated catch block
                throw new RuntimeException(exception);
            }
        }
        else
        {
            String updateStr = "UPDATE DatabaseProg.feedback_recensione set tipo = ?, "
                    + "where commento = ? and utente = ?";
            PreparedStatement st;
            try
            {
                st = connection.prepareStatement(updateStr);
                st.setBoolean(1, feedbackRecensione.isTipo());
                st.setInt(2, feedbackRecensione.getRecensione());
                st.setString(3, feedbackRecensione.getUtente());
                st.executeUpdate();

            } catch (SQLException exception){
                // TODO Auto-generated catch block
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public void delete(FeedbackRecensione feedbackRecensione) {
        String query = "DELETE FROM DatabaseProg.feedback_recensione WHERE utente = ? and recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, feedbackRecensione.getUtente());
            st.setInt(2, feedbackRecensione.getRecensione());
            st.executeUpdate();
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean alreadyInDatabase(String utente, int recensione) {
        String query = "select * from DatabaseProg.feedback_recensione where utente = ? and recensione = ?";
        try
        {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException exception){
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return false;
    }
}
