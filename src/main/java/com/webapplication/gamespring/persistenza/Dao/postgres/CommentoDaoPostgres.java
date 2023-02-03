package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.persistenza.Dao.CommentoDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class CommentoDaoPostgres implements CommentoDao {
    Connection connection;
    public CommentoDaoPostgres(Connection connection){this.connection = connection;}

    @Override
    public List<Commento> getReviewComments(int reviewID, int startIndex, int commentsSize) {


        List<Commento> commenti = new ArrayList<>();
        String query = "select * from DatabaseProg.commento where recensione = ? order by data desc offset ? limit ?";
        try {


            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, reviewID);
            st.setInt(2, startIndex);
            st.setInt(3, commentsSize);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Commento commento = readCommento(rs);
                FeedbackCommento feedbackCommento = DatabaseManager.getInstance().getFeedbackCommentoDao().findByPrimaryKey("Pie_Oxx", commento.getId());
                //Commento.Feedback feedback = feedbackCommento != null ? (feedbackCommento.isTipo() ? Commento.Feedback.Like : Commento.Feedback.Dislike) : Commento.Feedback.None;
                commenti.add(commento);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return commenti;
    }

    @Override
    public Commento readCommento(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String contenuto = resultSet.getString("contenuto");
        int numeroMiPiace = resultSet.getInt("numero_mi_piace");
        int numeroNonMiPiace = resultSet.getInt("numero_non_mi_piace");
        int recensione = resultSet.getInt("recensione");
        String utente = resultSet.getString("utente");
        OffsetDateTime offsetDateTime = resultSet.getObject("data", OffsetDateTime.class);
        return new Commento(id,contenuto,numeroMiPiace,numeroNonMiPiace,recensione,utente,offsetDateTime);
    }
    @Override
    public int save(Commento commento) throws SQLException {
        String insertStr = "INSERT INTO DatabaseProg.commento (contenuto, recensione, utente, data) VALUES (?, ?, ?, ?) returning id";
        PreparedStatement st = connection.prepareStatement(insertStr);
        st.setString(1, commento.getContenuto());
        st.setInt(2, commento.getRecensione());
        st.setString(3, commento.getUtente());
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
        Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
        st.setTimestamp(4, timestamp);
        ResultSet resultSet = st.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : -1;
    }

    @Override
    public void update(Commento commento) throws SQLException {
        String updateStr = "UPDATE DatabaseProg.commento set contenuto = ? where id = ?";
        PreparedStatement st = connection.prepareStatement(updateStr);
        st.setString(1, commento.getContenuto());
        st.setInt(2, commento.getId());
        st.executeUpdate();
    }


    @Override
    public void delete(Commento commento) throws SQLException {
        String deleteComment = "DELETE FROM DatabaseProg.commento WHERE id = ?";
        PreparedStatement st2 = connection.prepareStatement(deleteComment);
        st2.setLong(1, commento.getId());
        st2.executeUpdate();
    }
}
