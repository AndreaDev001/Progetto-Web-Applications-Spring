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

            while (rs.next()) {
                FeedbackRecensione feedbackRecensione = new FeedbackRecensione();

                feedbackRecensione.setUtente(rs.getString("utente"));
                feedbackRecensione.setRecensione(rs.getInt("recensione"));
                feedbackRecensione.setTipo(rs.getBoolean("tipo"));

                feedbackRecensioni.add(feedbackRecensione);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return feedbackRecensioni;
    }

    @Override
    public FeedbackRecensione findByPrimaryKey(String utente, int recensione) {
        FeedbackRecensione feedbackRecensione = null;
        String query = "select * from DatabaseProg.feedback_recensione where utente = ? and recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                feedbackRecensione = new FeedbackRecensione();
                feedbackRecensione.setUtente(rs.getString("utente"));
                feedbackRecensione.setRecensione(rs.getInt("recensione"));
                feedbackRecensione.setTipo(rs.getBoolean("tipo"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return feedbackRecensione;
    }

    @Override
    public void saveOrUpdate(FeedbackRecensione feedbackRecensione) {
        if (!alreadyInDatabase(feedbackRecensione.getUtente(), feedbackRecensione.getRecensione())) {
            String insertStr = "INSERT INTO DatabaseProg.feedback_recensione VALUES (?, ?, ?)";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(insertStr);

                st.setString(1, feedbackRecensione.getUtente());
                st.setInt(2, feedbackRecensione.getRecensione());
                st.setBoolean(3, feedbackRecensione.isTipo());


                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            String updateStr = "UPDATE DatabaseProg.feedback_recensione set tipo = ?, "
                    + "where commento = ? and utente = ?";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(updateStr);

                st.setBoolean(1, feedbackRecensione.isTipo());
                st.setInt(2, feedbackRecensione.getRecensione());
                st.setString(3, feedbackRecensione.getUtente());


                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean alreadyInDatabase(String utente, int recensione) {
        String query = "select * from DatabaseProg.feedback_recensione where utente = ? and recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
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
