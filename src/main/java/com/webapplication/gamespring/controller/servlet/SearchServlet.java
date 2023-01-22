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
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if(keyword.equals("")){
            List<Utente> all = DatabaseManager.getInstance().getUtenteDao().findAll();
            req.setAttribute("lista_utenti", all);

            RequestDispatcher dispacher = req.getRequestDispatcher("views/userList.html");
            dispacher.forward(req, resp);
        }
        else{
            List<Utente> utenti = DatabaseManager.getInstance().getUtenteDao().fuzzySearch(keyword);
            req.setAttribute("lista_utenti", utenti);

            RequestDispatcher dispacher = req.getRequestDispatcher("views/userList.html");
            dispacher.forward(req, resp);
        }



    }
}
