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

@WebServlet("/utenti")
public class Prova extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Wishlist> wishlists = DatabaseManager.getInstance().getWishlistDao().findByUser("a");
        HashMap<String, Integer> generi = new HashMap<String, Integer>();


        generi.put("action", 0);
        generi.put("indie", 0);
        generi.put("adventure", 0);
        generi.put("rpg", 0);
        generi.put("strategy", 0);
        generi.put("shooter", 0);
        generi.put("casual", 0);
        generi.put("simulation", 0);
        generi.put("puzzle", 0);
        generi.put("arcade", 0);
        generi.put("platformer", 0);
        generi.put("racing", 0);
        generi.put("sports", 0);
        generi.put("fighting", 0);
        generi.put("family", 0);
        generi.put("educational", 0);
        generi.put("cards", 0);


        String string [] = {"action", "indie", "adventure", "rpg", "strategy", "shooter", "casual", "simulation",
                            "puzzle", "arcade", "platformer", "racing", "sports", "fighting", "family", "educational", "cards"};


        List<Gioco> giochi = new ArrayList<Gioco>();

        for (Wishlist element : wishlists)
        {
            giochi.add(DatabaseManager.getInstance().getGiocoDao().findByPrimaryKey(element.getGioco()));
        }

        for (int i=0; i<giochi.size(); i++){

            generi.replace(giochi.get(i).getGenere(), generi.get(giochi.get(i).getGenere())+1);
            System.out.println(generi);
        }

        int max = Collections.max(generi.values());
        String index = "";

        for (Map.Entry<String, Integer> entry : generi.entrySet()){
            if(max == entry.getValue())
                index = entry.getKey();

        }

        generi.remove(index);

        int max2 = Collections.max(generi.values());
        String index2 = "";

        for (Map.Entry<String, Integer> entry : generi.entrySet()){
            if(max2 == entry.getValue())
                index2 = entry.getKey();

        }


        String api = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index ;
        String api2 = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + index2 ;


        req.setAttribute("ricerca_gioco1", api);
        req.setAttribute("ricerca_gioco2", api2);

        System.out.println(api);
        System.out.println(api2);
        RequestDispatcher dispacher = req.getRequestDispatcher("views/consigliati.html");
        dispacher.forward(req, resp);


    }
}