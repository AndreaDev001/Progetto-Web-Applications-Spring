package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.persistenza.Dao.CommentoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentoDaoPostgres implements CommentoDao {
    Connection connection;
    public CommentoDaoPostgres(Connection connection){this.connection = connection;}


    @Override
    public List<Commento> findAll() {
        List<Commento> commenti = new ArrayList<Commento>();
        String query = "select * from DatabaseProg.commento";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Commento commento = new Commento();

                commento.setId(rs.getInt("id"));
                commento.setContenuto(rs.getString("contenuto"));
                commento.setNumeroMiPiace(rs.getInt("numero_mi_piace"));
                commento.setNumeroNonMiPiace(rs.getInt("numero_non_mi_piace"));
                commento.setRecensione(rs.getInt("recensione"));
                commento.setUtente(rs.getString("utente"));

                commenti.add(commento);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return commenti;
    }

    @Override
    public Commento findByPrimaryKey(int id) {
        Commento commento = null;
        String query = "select * from DatabaseProg.commento where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                commento = new Commento();
                commento.setId(rs.getInt("id"));
                commento.setContenuto(rs.getString("contenuto"));
                commento.setNumeroMiPiace(rs.getInt("numero_mi_piace"));
                commento.setNumeroNonMiPiace(rs.getInt("numero_non_mi_piace"));
                commento.setRecensione(rs.getInt("recensione"));
                commento.setUtente(rs.getString("utente"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return commento;
    }


    @Override
    public void save(Commento commento) {
            String insertStr = "INSERT INTO DatabaseProg.commento (contenuto, numero_mi_piace, numero_non_mi_piace, recensione, utente) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(insertStr);

                st.setString(1, commento.getContenuto());
                st.setInt(2, commento.getNumeroMiPiace());
                st.setInt(3, commento.getNumeroNonMiPiace());
                st.setInt(4, commento.getRecensione());
                st.setString(5, commento.getUtente());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    @Override
    public void update(Commento commento) {
            String updateStr = "UPDATE DatabaseProg.commento set contenuto = ?, "
                    + "numero_mi_piace = ?, "
                    + "numero_non_mi_piace = ?, "
                    + "recensione = ?, "
                    + "utente = ? "
                    + "where id = ?";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(updateStr);

                st.setString(1, commento.getContenuto());
                st.setInt(2, commento.getNumeroMiPiace());
                st.setInt(3, commento.getNumeroNonMiPiace());
                st.setInt(4, commento.getRecensione());
                st.setString(5, commento.getUtente());
                st.setInt(6, commento.getId());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }


    @Override
    public void delete(Commento commento) {
        String query = "DELETE FROM DatabaseProg.feedback_commento WHERE commento = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, commento.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.commento WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, commento.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
