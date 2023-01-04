package com.webapplication.gamespring.persistenza.Dao.postgres;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDaoPostgres implements UtenteDao {

    Connection connection;
    public UtenteDaoPostgres(Connection connection){this.connection = connection;}

    @Override
    public List<Utente> findAll() {
        List<Utente> utenti = new ArrayList<Utente>();
        String query = "select * from DatabaseProg.utente";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setAmministratore(rs.getBoolean("amministratore"));
                utente.setBandito(rs.getBoolean("bandito"));

                utenti.add(utente);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return utenti;
    }

    @Override
    public Utente findByPrimaryKey(String username) {
        Utente utente = null;
        String query = "select * from DatabaseProg.utente where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setAmministratore(rs.getBoolean("amministratore"));
                utente.setBandito(rs.getBoolean("bandito"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return utente;
    }

    @Override
    public List<Utente> fuzzySearch(String username) {
        List<Utente> utenti = new ArrayList<Utente>();
        String query = "select * from DatabaseProg.utente where username LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, "%"+username+"%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setAmministratore(rs.getBoolean("amministratore"));
                utente.setBandito(rs.getBoolean("bandito"));

                utenti.add(utente);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return utenti;
    }

    @Override
    public void saveOrUpdate(Utente utente) {
        if (!alreadyInDatabase(utente.getUsername())) {
            String insertStr = "INSERT INTO DatabaseProg.utente VALUES (?, ?, ?, ?, ?)";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(insertStr);

                st.setString(1, utente.getUsername());
                st.setString(2, utente.getEmail());
                st.setString(3, utente.getPassword());
                st.setBoolean(4, utente.isAmministratore());
                st.setBoolean(5, utente.isBandito());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            String updateStr = "UPDATE DatabaseProg.utente set email = ?, "
                    + "password = ?, "
                    + "amministratore = ?, "
                    + "bandito = ? "
                    + "where username = ?";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(updateStr);

                st.setString(1, utente.getEmail());
                st.setString(2, utente.getPassword());
                st.setBoolean(3, utente.isAmministratore());
                st.setBoolean(4, utente.isBandito());
                st.setString(5, utente.getUsername());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String username) {

        String query = "DELETE FROM DatabaseProg.feedback_commento WHERE utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        query = "DELETE FROM DatabaseProg.feedback_recensione WHERE utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.segnalazione WHERE utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.commento WHERE utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.recensione WHERE utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        query = "DELETE FROM DatabaseProg.wishlist WHERE utente = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        query = "DELETE FROM DatabaseProg.utente WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean alreadyInDatabase(String username) {
        String query = "select * from DatabaseProg.utente where username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, username);
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
