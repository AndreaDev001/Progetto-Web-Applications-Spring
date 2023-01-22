package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.sql.*;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {

    Connection connection;
    public RecensioneDaoPostgres(Connection connection){this.connection = connection;}

    @Override
    public List<Recensione> getGameReviews(int gameID) {
        List<Recensione> recensioni = new ArrayList<Recensione>();
        String query = "select * from DatabaseProg.recensione where gioco = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, gameID);
            ResultSet rs = st.executeQuery();
            while (rs.next())
                recensioni.add(readRecensione(rs));
        }
        catch (SQLException exception){
            throw new RuntimeException(exception);
        }
        return recensioni;
    }

    @Override
    public Recensione findByPrimaryKey(int id) {
        String query = "select * from DatabaseProg.recensione where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return readRecensione(rs);
        }
        catch (SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int save(Recensione recensione) throws SQLException {
        String query = "insert into DatabaseProg.recensione(titolo, contenuto,voto, utente, gioco, data) values (?, ?, ?, ?, ?, ?) returning id";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, recensione.getTitolo());
        st.setString(2, recensione.getContenuto());
        st.setInt(3, recensione.getVoto());
        st.setString(4, recensione.getUtente());
        st.setLong(5, recensione.getGioco());
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
        Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
        st.setTimestamp(6, timestamp);
        ResultSet resultSet = st.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : -1;
    }
    @Override
    public Recensione getUserReview(String username, int gameID) {
        String query = "select * FROM DatabaseProg.recensione where utente=? and gioco=?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setInt(2,gameID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return readRecensione(resultSet);
        }
        catch (SQLException exception){
            throw  new RuntimeException(exception);
        }
        return null;
    }

    @Override
    public Recensione readRecensione(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String titolo = resultSet.getString(2);
        String contenuto = resultSet.getString(3);
        int voto = resultSet.getInt(4);
        int numeroMiPiace = resultSet.getInt(5);
        int numeroNonMiPiace = resultSet.getInt(6);
        String utente = resultSet.getString(7);
        int gioco = resultSet.getInt(8);
        OffsetDateTime offsetDateTime = resultSet.getObject("data", OffsetDateTime.class);
        return new Recensione(id,titolo,contenuto,voto,numeroMiPiace,numeroNonMiPiace,utente,gioco,offsetDateTime);
    }
    @Override
    public List<Recensione> getUserReviews(String username){
        List<Recensione> recensioni = new ArrayList<>();
        String query = "select * from DatabaseProg.recensione where utente=?";
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                recensioni.add(readRecensione(resultSet));
        }
        catch (SQLException exception){
            throw  new RuntimeException(exception);
        }
        return recensioni;
    }
    @Override
    public void delete(int recensione) {

        String query = "DELETE FROM DatabaseProg.feedback_recensione WHERE recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, recensione);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "SELECT * FROM DatabaseProg.commento WHERE recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, recensione);
            ResultSet rs = st.executeQuery();



            while (rs.next()){

                String query2 = "DELETE FROM DatabaseProg.feedback_commenti WHERE commento = ?";
                PreparedStatement st2 = connection.prepareStatement(query2);
                st2.setInt(1, rs.getInt("id"));
                st2.executeUpdate();

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.segnalazione WHERE recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, recensione);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        query = "DELETE FROM DatabaseProg.commento WHERE recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, recensione);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.recensione WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, recensione);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Recensione recensione) {
        String query = "update DatabaseProg.recensione set titolo = ?, contenuto = ?, voto = ? where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, recensione.getTitolo());
            st.setString(2, recensione.getContenuto());
            st.setInt(3, recensione.getVoto());
            st.setLong(4, recensione.getId());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

}
