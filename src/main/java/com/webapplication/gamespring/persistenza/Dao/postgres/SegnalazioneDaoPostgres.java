package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.SegnalazioneDao;

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

            while (rs.next()) {
                Segnalazione segnalazione = new Segnalazione();
                segnalazione.setRecensione(rs.getInt("recensione"));
                segnalazione.setUtente(rs.getString("utente"));
                segnalazione.setMotivazione(rs.getString("motivazione"));

                segnalazioni.add(segnalazione);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return segnalazioni;
    }

    @Override
    public Segnalazione findByPrimaryKey(int recensione, String utente) {
       Segnalazione segnalazione = null;
        String query = "select * from DatabaseProg.segnalazione where utente = ? and recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, recensione);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                segnalazione = new Segnalazione();
                segnalazione.setRecensione(rs.getInt("recensione"));
                segnalazione.setUtente(rs.getString("utente"));
                segnalazione.setMotivazione(rs.getString("motivazione"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return segnalazione;
    }

    @Override
    public void saveOrUpdate(Segnalazione segnalazione) {
        if (!alreadyInDatabase(segnalazione.getRecensione(), segnalazione.getUtente())) {
            String insertStr = "INSERT INTO DatabaseProg.segnalazione VALUES (?, ?, ?)";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(insertStr);

                st.setInt(1, segnalazione.getRecensione());
                st.setString(2, segnalazione.getUtente());
                st.setString(3, segnalazione.getMotivazione());


                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            String updateStr = "UPDATE DatabaseProg.segnalazione set motivazione = ?, "
                    + "where recensione = ? and utente = ?";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(updateStr);

                st.setString(1, segnalazione.getMotivazione());
                st.setInt(2, segnalazione.getRecensione());
                st.setString(3, segnalazione.getUtente());


                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Segnalazione segnalazione) {
        String query = "DELETE FROM DatabaseProg.segnalazione WHERE utente = ? and recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, segnalazione.getUtente());
            st.setInt(2, segnalazione.getRecensione());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
