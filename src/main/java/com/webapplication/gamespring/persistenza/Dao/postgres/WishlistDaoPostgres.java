package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.model.Wishlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.webapplication.gamespring.persistenza.Dao.WishlistDao;

public class WishlistDaoPostgres implements WishlistDao {

    Connection connection;
    public WishlistDaoPostgres(Connection connection){this.connection = connection;}


    @Override
    public List<Wishlist> findAll() {
        List<Wishlist> wishlists = new ArrayList<Wishlist>();
        String query = "select * from DatabaseProg.wishlist";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setGioco(rs.getInt("gioco"));
                wishlist.setUtente(rs.getString("utente"));

                wishlists.add(wishlist);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wishlists;
    }

    @Override
    public Wishlist findByPrimaryKey(int gioco, String utente) {
        Wishlist wishlist = null;
        String query = "select * from DatabaseProg.wishlist where utente = ? and gioco = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, gioco);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                wishlist = new Wishlist();
                wishlist.setGioco(rs.getInt("gioco"));
                wishlist.setUtente(rs.getString("utente"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wishlist;
    }

    @Override
    public List<Wishlist> findByUser(String utente) {
        List<Wishlist> wishlists = new ArrayList<Wishlist>();
        String query = "select * from DatabaseProg.wishlist where utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setGioco(rs.getInt("gioco"));
                wishlist.setUtente(rs.getString("utente"));

                wishlists.add(wishlist);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wishlists;
    }

    @Override
    public void save(Wishlist wishlist) {
        if (!alreadyInDatabase(wishlist.getGioco(), wishlist.getUtente())) {
            String insertStr = "INSERT INTO DatabaseProg.wishlist VALUES (?, ?)";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(insertStr);

                st.setString(1, wishlist.getUtente());
                st.setInt(2, wishlist.getGioco());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Wishlist wishlist) {
        String query = "DELETE FROM DatabaseProg.wishlist WHERE utente = ? and gioco = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, wishlist.getUtente());
            st.setInt(2, wishlist.getGioco());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean alreadyInDatabase(int gioco, String utente) {
        String query = "select * from DatabaseProg.wishlist where utente = ? and gioco = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, gioco);
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
