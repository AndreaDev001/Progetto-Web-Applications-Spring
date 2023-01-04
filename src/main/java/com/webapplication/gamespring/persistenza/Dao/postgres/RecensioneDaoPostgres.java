package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {

    Connection connection;
    public RecensioneDaoPostgres(Connection connection){this.connection = connection;}


    @Override
    public List<Recensione> findAll() {
        List<Recensione> recensioni = new ArrayList<Recensione>();
        String query = "select * from DatabaseProg.recensione";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setId(rs.getInt("id"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setContenuto(rs.getString("contenuto"));
                recensione.setVoto(rs.getInt("voto"));
                recensione.setNumeroMiPiace(rs.getInt("numero_mi_piace"));
                recensione.setNumeroNonMiPiace(rs.getInt("numero_non_mi_piace"));
                recensione.setUtente(rs.getString("utente"));
                recensione.setGioco(rs.getInt("gioco"));

                recensioni.add(recensione);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return recensioni;
    }

    @Override
    public Recensione findByPrimaryKey(int id) {
        Recensione recensione = null;
        String query = "select * from DatabaseProg.recensione where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                recensione = new Recensione();
                recensione.setId(rs.getInt("id"));
                recensione.setTitolo(rs.getString("titolo"));
                recensione.setContenuto(rs.getString("contenuto"));
                recensione.setVoto(rs.getInt("voto"));
                recensione.setNumeroMiPiace(rs.getInt("numero_mi_piace"));
                recensione.setNumeroNonMiPiace(rs.getInt("numero_non_mi_piace"));
                recensione.setUtente(rs.getString("utente"));
                recensione.setGioco(rs.getInt("gioco"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return recensione;
    }



    @Override
    public int save(Recensione recensione) {
        String query = "insert into DatabaseProg.recensione(titolo, contenuto,voto, utente, gioco) values (?, ?, ?, ?, ?) returning id";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, recensione.getTitolo());
            st.setString(2, recensione.getContenuto());
            st.setInt(3, recensione.getVoto());
            st.setString(4, recensione.getUtente());
            st.setLong(5, recensione.getGioco());
            ResultSet resultSet = st.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : -1;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void delete(Recensione recensione) {

        String query = "DELETE FROM DatabaseProg.feedback_recensione WHERE recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, recensione.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "SELECT FROM DatabaseProg.commento WHERE recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, recensione.getId());
            ResultSet rs = st.executeQuery();


            while (rs.next()){
                String query2 = "DELETE FROM DatabaseProg.feedback_commento WHERE commento = ?";
                PreparedStatement st2 = connection.prepareStatement(query2);
                st.setLong(1, rs.getInt("commento"));
                st.executeUpdate();

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        query = "DELETE FROM DatabaseProg.commento WHERE recensione = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, recensione.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        query = "DELETE FROM DatabaseProg.recensione WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, recensione.getId());
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
