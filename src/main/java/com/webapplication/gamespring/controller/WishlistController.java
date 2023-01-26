package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.model.Wishlist;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class WishlistController {

    @GetMapping("/wishlist")
    public String wishlistPage(HttpServletRequest req, HttpServletResponse response)
    {
        Utente utente = (Utente) req.getSession().getAttribute("user");
        //if user is null redirect client to the login page
        if(utente == null)
            return "redirect:login";
        List<Wishlist> userWishList = DatabaseManager.getInstance().getWishlistDao().findByUser(utente.getUsername());
        List<Gioco> games = new LinkedList<>();
        userWishList.forEach((wishlist -> games.add(DatabaseManager.getInstance().getGiocoDao().findByPrimaryKey(wishlist.getGioco()))));
        req.setAttribute("utente", utente);
        req.setAttribute("giochi", games);
        return "wishlist.html";
    }
}
