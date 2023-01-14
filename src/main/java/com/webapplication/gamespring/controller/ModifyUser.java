package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.Recensione;
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

        Utente user = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(username);

        req.setAttribute("modify_utente", user);

        RequestDispatcher dispacher = req.getRequestDispatcher("views/modificaUtenti.html");
        dispacher.forward(req, resp);



    }
}
