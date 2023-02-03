package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.model.Wishlist;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebServlet("/recommended")
public class RecommendedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Utente utente = (Utente)session.getAttribute("user");
        if(utente == null){
            resp.sendRedirect("http://localhost:8080/notPermitted");
            return;
        }
        //cerco tutti i giochi della wishlist di un utente e successivamente creo un hashmap per capire quali sono i giochi piu
        //popolari della sua wishlist
        List<Wishlist> wishlists = DatabaseManager.getInstance().getWishlistDao().findByUser(utente.getUsername());
        HashMap<String, Integer> genres = new HashMap<String, Integer>();
        genres.put("action", 0);
        genres.put("indie", 0);
        genres.put("adventure", 0);
        genres.put("role-playing-games-rpg", 0);
        genres.put("strategy", 0);
        genres.put("shooter", 0);
        genres.put("casual", 0);
        genres.put("simulation", 0);
        genres.put("puzzle", 0);
        genres.put("arcade", 0);
        genres.put("platformer", 0);
        genres.put("racing", 0);
        genres.put("massively-multiplayer", 0);
        genres.put("sports", 0);
        genres.put("fighting", 0);
        genres.put("family", 0);
        genres.put("board-games", 0);
        genres.put("educational", 0);
        genres.put("cards", 0);
        List<Gioco> games = new ArrayList<Gioco>();
        for (Wishlist element : wishlists)
            games.add(DatabaseManager.getInstance().getGiocoDao().findByPrimaryKey(element.getGioco()));
        for (Gioco game : games) {
            genres.replace(game.getGenere(), genres.get(game.getGenere()) + 1);

        }
        //prendo i generi più popolari della wishlist
        int max = Collections.max(genres.values());
        String index = "";
        for (Map.Entry<String, Integer> entry : genres.entrySet()){
            if(max == entry.getValue())
                index = entry.getKey();

        }

        genres.remove(index);

        max = Collections.max(genres.values());
        String index2 = "";

        for (Map.Entry<String, Integer> entry : genres.entrySet()){
            if(max == entry.getValue())
                index2 = entry.getKey();

        }

        genres.remove(index2);

        max = Collections.max(genres.values());
        String index3 = "";

        for (Map.Entry<String, Integer> entry : genres.entrySet()){
            if(max == entry.getValue())
                index3 = entry.getKey();

        }

        genres.remove(index3);

        max = Collections.max(genres.values());
        String index4 = "";

        for (Map.Entry<String, Integer> entry : genres.entrySet()){
            if(max == entry.getValue())
                index4 = entry.getKey();

        }

        //passo i dati delle api per reperire i giochi

        String api = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index ;
        String api2 = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index2 ;
        String api3 = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index3 ;
        String api4 = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index4;


        req.setAttribute("api1", api);
        req.setAttribute("api2", api2);
        req.setAttribute("api3", api3);
        req.setAttribute("api4", api4);

        req.setAttribute("gen1", index);
        req.setAttribute("gen2", index2);
        req.setAttribute("gen3", index3);
        req.setAttribute("gen4", index4);;

        RequestDispatcher dispatcher = req.getRequestDispatcher("views/recommended.html");
        dispatcher.forward(req, resp);
    }
}