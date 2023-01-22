package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.SegnalazioneDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SegnalazioneDaoPostgres implements SegnalazioneDao {

    Connection connection;
    public SegnalazioneDaoPostgres(Connection connection){this.connection = connection;}

    @Override
    public List<Segnalazione> findAll() {
        List<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
        String query = "select * from DatabaseProg.segnalazione";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
                segnalazioni.add(readSegnalazione(rs));
        } catch (SQLException exception){
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return segnalazioni;
    }

    @Override
    public Segnalazione findByPrimaryKey(int recensione, String utente) {
        String query = "select * from DatabaseProg.segnalazione where utente = ? and recensione = ?";
        try
        {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return readSegnalazione(rs);

        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return null;
    }
    @Override
    public Segnalazione readSegnalazione(ResultSet resultSet) throws SQLException {
        int idRecensione = resultSet.getInt("recensione");
        String utente = resultSet.getString("utente");
        String motivazione = resultSet.getString("motivazione");
        return new Segnalazione(DatabaseManager.getInstance().getRecensioneDao().findByPrimaryKey(idRecensione),utente,motivazione);
    }
    @Override
    public void saveOrUpdate(Segnalazione segnalazione) {
        if (!alreadyInDatabase(segnalazione.getRecensione().getId(), segnalazione.getUtente())) {
            String insertStr = "INSERT INTO DatabaseProg.segnalazione VALUES (?, ?, ?)";
            PreparedStatement st;
            try
            {
                st = connection.prepareStatement(insertStr);
                st.setInt(1, segnalazione.getRecensione().getId());
                st.setString(2, segnalazione.getUtente());
                st.setString(3, segnalazione.getMotivazione());
                st.executeUpdate();

            } catch (SQLException exception){
                // TODO Auto-generated catch block
                throw new RuntimeException(exception);
            }
        }
        else
        {
            String updateStr = "UPDATE DatabaseProg.segnalazione set motivazione = ?, "
                    + "where recensione = ? and utente = ?";
            PreparedStatement st;
            try
            {
                st = connection.prepareStatement(updateStr);
                st.setString(1, segnalazione.getMotivazione());
                st.setInt(2, segnalazione.getRecensione().getId());
                st.setString(3, segnalazione.getUtente());
                st.executeUpdate();
            } catch (SQLException exception) {
                // TODO Auto-generated catch block
                throw new RuntimeException(exception);
            }
        }
    }
    @Override
    public void delete(int recensione, String utente) {
        String query = "DELETE FROM DatabaseProg.segnalazione WHERE utente = ? and recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
            st.executeUpdate();
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
    }
    @Override
    public boolean alreadyInDatabase(int recensione, String utente) {
        String query = "select * from DatabaseProg.utente where utente = ? and recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
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
