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

    /**
     *
     * Invocata quando si accede alla pagina dedicata ai consigliati,
     * crea una pagina in base ai risultati contenuti dalla wishlist nel database
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
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
        String[] genres = {"action","indie","adventure","role-playing-games-rpg","strategy","shooter","casual","simulation","puzzle","arcade","platformer","racing",
        "massively-multiplayer","sports","fighting","family","board-games","educational","cards"};
        HashMap<String, Integer> genreAmount = new HashMap<String, Integer>();
        for(String current : genres)
            genreAmount.put(current,0);
        List<Gioco> games = new ArrayList<Gioco>();
        for (Wishlist element : wishlists)
            games.add(DatabaseManager.getInstance().getGiocoDao().findByPrimaryKey(element.getGioco()));
        for (Gioco game : games)
            genreAmount.replace(game.getGenere(), genreAmount.get(game.getGenere()) + 1);
        Object[] values = genreAmount.entrySet().toArray();
        Arrays.sort(values,((o1, o2) -> {
            return ((Map.Entry<String,Integer>)o2).getValue().compareTo(((Map.Entry<String,Integer>)o1).getValue());
        }));
        List<String> indexes = new ArrayList<>();
        for(int i = 0;i < 4;i++){
            Map.Entry<String,Integer> value = (Map.Entry<String,Integer>)values[i];
            indexes.add(value.getKey());
        }
        for (Gioco game : games)
            genres.replace(game.getGenere(), genres.get(game.getGenere()) + 1);

        //passo i dati delle api per reperire i giochi
        for(int i = 0;i < 4;i++){
            addAPIAttribute(req,i,indexes.get(i));
            req.setAttribute("gen" + (i + 1),indexes.get(i));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/recommended.html");
        dispatcher.forward(req, resp);
    }
    private void addAPIAttribute(HttpServletRequest request,int index,String genreIndex){
        String api = "https://api.rawg.io/api/games?key=9970cebdf7b244e6bc80319c9e29e10c&genres=" + genreIndex;
        request.setAttribute("api" + (index + 1),api);
    }
}