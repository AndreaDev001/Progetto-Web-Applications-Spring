package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.model.Wishlist;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet("/recommended")
public class Recommended extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Wishlist> wishlists = DatabaseManager.getInstance().getWishlistDao().findByUser("a");
        HashMap<String, Integer> genres = new HashMap<String, Integer>();

        genres.put("action", 0);
        genres.put("indie", 0);
        genres.put("adventure", 0);
        genres.put("rpg", 0);
        genres.put("strategy", 0);
        genres.put("shooter", 0);
        genres.put("casual", 0);
        genres.put("simulation", 0);
        genres.put("puzzle", 0);
        genres.put("arcade", 0);
        genres.put("platformer", 0);
        genres.put("racing", 0);
        genres.put("sports", 0);
        genres.put("fighting", 0);
        genres.put("family", 0);
        genres.put("educational", 0);
        genres.put("cards", 0);


        List<Gioco> games = new ArrayList<Gioco>();

        for (Wishlist element : wishlists)
        {
            games.add(DatabaseManager.getInstance().getGiocoDao().findByPrimaryKey(element.getGioco()));
        }

        for (int i=0; i<games.size(); i++){

            genres.replace(games.get(i).getGenere(), genres.get(games.get(i).getGenere()) +1);
            System.out.println(genres);
        }

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


        String api = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index ;
        String api2 = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index2 ;
        String api3 = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index3 ;


        req.setAttribute("api1", api);
        req.setAttribute("api2", api2);
        req.setAttribute("api3", api3);

        req.setAttribute("gen1", index);
        req.setAttribute("gen2", index2);
        req.setAttribute("gen3", index3);

        System.out.println(api);
        System.out.println(api2);
        System.out.println(api3);
        RequestDispatcher dispacher = req.getRequestDispatcher("views/recommended.html");
        dispacher.forward(req, resp);


    }
}