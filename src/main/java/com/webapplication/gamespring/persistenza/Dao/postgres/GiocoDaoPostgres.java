package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.GiocoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiocoDaoPostgres implements GiocoDao {

    Connection connection;
    public GiocoDaoPostgres(Connection connection){this.connection = connection;}

    @Override
    public List<Gioco> findAll() {
        List<Gioco> giochi = new ArrayList<Gioco>();
        String query = "select * from DatabaseProg.gioco";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
                giochi.add(readGioco(rs));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return giochi;
    }
    @Override
    public Gioco findByPrimaryKey(int id) {
        String query = "select * from DatabaseProg.gioco where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return readGioco(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Gioco readGioco(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String genere = resultSet.getString("genere");
        String titolo = resultSet.getString("titolo");
        String immagine = resultSet.getString("immagine");
        return new Gioco(id,genere,titolo,immagine);
    }

    @Override
    public boolean alreadyInDatabase(int id) {

        String query = "select * from DatabaseProg.gioco where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void saveOrUpdate(Gioco gioco) {
        if (!alreadyInDatabase(gioco.getId())) {
            String insertStr = "INSERT INTO DatabaseProg.gioco VALUES (?, ?, ?, ?)";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(insertStr);

                st.setLong(1, gioco.getId());
                st.setString(2, gioco.getGenere());
                st.setString(3, gioco.getTitolo());
                st.setString(4, gioco.getImmagine());
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void delete(Gioco gioco) {
        String query = "DELETE FROM DatabaseProg.wishlist WHERE gioco = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, gioco.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.gioco WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, gioco.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
