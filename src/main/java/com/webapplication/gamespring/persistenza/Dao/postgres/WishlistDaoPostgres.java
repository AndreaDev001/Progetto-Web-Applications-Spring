package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Wishlist;
import com.webapplication.gamespring.persistenza.Dao.WishlistDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            while (rs.next())
                wishlists.add(readWishlist(rs));
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return wishlists;
    }
    @Override
    public Wishlist findByPrimaryKey(int gioco, String utente) {
        String query = "select * from DatabaseProg.wishlist where utente = ? and gioco = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            st.setInt(2, gioco);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return readWishlist(rs);
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return null;
    }
    @Override
    public Wishlist readWishlist(ResultSet resultSet) throws SQLException {
        int gioco = resultSet.getInt("gioco");
        String utente = resultSet.getString("utente");
        return new Wishlist(gioco,utente);
    }
    @Override
    public List<Wishlist> findByUser(String utente) {
        List<Wishlist> wishlists = new ArrayList<Wishlist>();
        String query = "select * from DatabaseProg.wishlist where utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, utente);
            ResultSet rs = st.executeQuery();
            while (rs.next())
                wishlists.add(readWishlist(rs));
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
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
            } catch (SQLException exception){
                // TODO Auto-generated catch block
                throw new RuntimeException(exception);
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
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
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
            if (rs.next())
                return true;
        } catch (SQLException exception) {
            // TODO Auto-generated catch block
            throw new RuntimeException(exception);
        }
        return false;
    }
}
