package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.persistenza.Dao.FeedbackCommentoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackCommentoDaoPostgres implements FeedbackCommentoDao {
    Connection connection;
    public FeedbackCommentoDaoPostgres(Connection connection){this.connection = connection;}


    @Override
    public List<FeedbackCommento> findAll() {
        List<FeedbackCommento> feedbackCommenti = new ArrayList<FeedbackCommento>();
        String query = "select * from DatabaseProg.feedback_commento";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                FeedbackCommento feedbackCommento = new FeedbackCommento();

                feedbackCommento.setUtente(rs.getString("utente"));
                feedbackCommento.setCommento(rs.getInt("commento"));
                feedbackCommento.setTipo(rs.getBoolean("tipo"));

                feedbackCommenti.add(feedbackCommento);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return feedbackCommenti;
    }

    @Override
    public FeedbackCommento findByPrimaryKey(String utente, int commento) {
        FeedbackCommento feedbackCommento = null;
        String query = "select * from DatabaseProg.feedback_commento where utente = ? and commento = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, commento);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                feedbackCommento = new FeedbackCommento();
                feedbackCommento.setUtente(rs.getString("utente"));
                feedbackCommento.setCommento(rs.getInt("commento"));
                feedbackCommento.setTipo(rs.getBoolean("tipo"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return feedbackCommento;
    }

    @Override
    public void saveOrUpdate(FeedbackCommento feedbackCommento) {
        if (!alreadyInDatabase(feedbackCommento.getUtente(), feedbackCommento.getCommento())) {
            String insertStr = "INSERT INTO DatabaseProg.feedback_commento VALUES (?, ?, ?)";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(insertStr);

                st.setString(1, feedbackCommento.getUtente());
                st.setInt(2, feedbackCommento.getCommento());
                st.setBoolean(3, feedbackCommento.isTipo());


                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            String updateStr = "UPDATE DatabaseProg.feedback_commento set tipo = ?, "
                    + "where commento = ? and utente = ?";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(updateStr);

                st.setBoolean(1, feedbackCommento.isTipo());
                st.setInt(2, feedbackCommento.getCommento());
                st.setString(3, feedbackCommento.getUtente());


                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(FeedbackCommento feedbackCommento) {
        String query = "DELETE FROM DatabaseProg.feedback_commento WHERE utente = ? and commento = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, feedbackCommento.getUtente());
            st.setInt(2, feedbackCommento.getCommento());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean alreadyInDatabase(String utente, int commento) {
            String query = "select * from DatabaseProg.feedback_commento where utente = ? and commento = ?";
            try {
                PreparedStatement st = connection.prepareStatement(query);
                st.setString(1, utente);
                st.setInt(2, commento);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    System.out.println("v");
                    return true;
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("f");
            return false;
    }
}
