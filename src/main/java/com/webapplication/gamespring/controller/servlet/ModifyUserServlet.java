package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/modifyUser")
public class ModifyUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        //cerco tutti i dati dell'utente e carico la pagina per poter modificare l'utente
        Utente user = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(username);

        req.setAttribute("modify_utente", user);

        RequestDispatcher dispacher = req.getRequestDispatcher("views/modifyUser.html");
        dispacher.forward(req, resp);



    }
}
