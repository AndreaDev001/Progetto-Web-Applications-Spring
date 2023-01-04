package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/modifyUser")
public class ModifyUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String admin= req.getParameter("admin");
        String ban = req.getParameter("ban");

        Utente u = new Utente();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);

        if(admin == null){
            u.setAmministratore(false);
        }
        else
            u.setAmministratore(true);

        if(ban == null){
            u.setBandito(false);
        }
        else
            u.setBandito(true);

        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(u);

        List<Utente> utenti = DatabaseManager.getInstance().getUtenteDao().findAll();

        req.setAttribute("lista_utenti", utenti);

        RequestDispatcher dispacher = req.getRequestDispatcher("views/utenti.html");
        dispacher.forward(req, resp);

    }
}
