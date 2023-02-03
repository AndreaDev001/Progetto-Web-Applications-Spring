package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.FeedbackRecensione;
import com.webapplication.gamespring.persistenza.Dao.FeedbackRecensioneDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackRecensioneDaoPostgres implements FeedbackRecensioneDao {
    Connection connection;
    public FeedbackRecensioneDaoPostgres(Connection connection){this.connection = connection;}

    @Override
    public FeedbackRecensione findByPrimaryKey(String utente, int recensioneID) throws SQLException {
        String query = "select * from databaseProg.feedback_recensione where utente = ? and recensione = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, utente);
        st.setInt(2, recensioneID);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            FeedbackRecensione feedbackRecensione = new FeedbackRecensione();
            feedbackRecensione.setUtente(rs.getString("utente"));
            feedbackRecensione.setRecensione(rs.getInt("recensione"));
            feedbackRecensione.setTipo(rs.getBoolean("tipo"));
            return feedbackRecensione;
        }
        return null;
    }

    @Override
    public boolean saveOrUpdate(FeedbackRecensione feedbackRecensione) throws SQLException {
        String query = "select databaseProg.updateLikeOrDislikeReview (?, ?, ?)";
        PreparedStatement st = this.connection.prepareStatement(query);
        st.setInt(1, feedbackRecensione.getRecensione());
        st.setBoolean(2, feedbackRecensione.isTipo());
        st.setString(3, feedbackRecensione.getUtente());
        ResultSet resultSet = st.executeQuery();
        if(resultSet.next())
            return resultSet.getBoolean(1);
        return false;
    }

}
