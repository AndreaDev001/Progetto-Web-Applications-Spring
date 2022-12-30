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

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String username = req.getParameter("username");


            DatabaseManager.getInstance().getUtenteDao().delete(username);
            List<Utente> utenti = DatabaseManager.getInstance().getUtenteDao().findAll();
            req.setAttribute("lista_utenti", utenti);
            RequestDispatcher dispacher = req.getRequestDispatcher("views/utenti.html");
            dispacher.forward(req, resp);



        }
}
