package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Segnalazione;
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
    public void save(Segnalazione segnalazione) throws SQLException {
        String insertStr = "INSERT INTO DatabaseProg.segnalazione VALUES (?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(insertStr);
        st.setInt(1, segnalazione.getRecensione().getId());
        st.setString(2, segnalazione.getUtente());
        st.setString(3, segnalazione.getMotivazione());
        st.executeUpdate();


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
}
