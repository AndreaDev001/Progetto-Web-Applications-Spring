package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.persistenza.Dao.FeedbackCommentoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackCommentoDaoPostgres implements FeedbackCommentoDao
{

    Connection connection;
    public FeedbackCommentoDaoPostgres(Connection connection){this.connection = connection;}


    @Override
    public List<FeedbackCommento> findAll() {
        List<FeedbackCommento> feedbackCommenti = new ArrayList<FeedbackCommento>();
        String query = "select * from DatabaseProg.feedback_commenti";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                feedbackCommenti.add(readFeedbackCommento(rs));
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return feedbackCommenti;
    }

    @Override
    public FeedbackCommento findByPrimaryKey(String utente, int commento) {
        String query = "select * from DatabaseProg.feedback_commenti where utente = ? and commento = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, commento);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return readFeedbackCommento(rs);

        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return null;
    }
    @Override
    public FeedbackCommento readFeedbackCommento(ResultSet resultSet) throws SQLException {
        String utente = resultSet.getString("utente");
        int commento = resultSet.getInt("commento");
        boolean tipo = resultSet.getBoolean("tipo");
        return new FeedbackCommento(utente,commento,tipo);
    }
    @Override
    public boolean saveOrUpdate(FeedbackCommento feedbackCommento) {
        try {

            String query = "select databaseprog.updateLikeOrDislike(?, ?, ?)";
            PreparedStatement st = this.connection.prepareStatement(query);
            st.setInt(1, feedbackCommento.getCommento());
            st.setBoolean(2, feedbackCommento.isTipo());
            st.setString(3, feedbackCommento.getUtente());
            ResultSet resultSet = st.executeQuery();
            return resultSet.next() && resultSet.getBoolean(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(FeedbackCommento feedbackCommento) {
        String query = "DELETE FROM databaseprog.feedback_commenti WHERE utente = ? and commento = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, feedbackCommento.getUtente());
            st.setInt(2, feedbackCommento.getCommento());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean alreadyInDatabase(String utente, int commento) {
            String query = "select * from DatabaseProg.feedback_commento where utente = ? and commento = ?";
            try {
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, utente);
                st.setInt(2, commento);
                ResultSet rs = st.executeQuery();
                if (rs.next())
                    return true;

            } catch (SQLException exception) {
                // TODO Auto-generated catch block
                throw new RuntimeException(exception);
            }
            return false;
    }
}
