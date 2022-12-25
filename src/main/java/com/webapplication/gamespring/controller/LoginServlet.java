package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet ("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UtenteDao udao = DatabaseManager.getInstance().getUtenteDao();
        Utente utente = udao.findByPrimaryKey(username);

        boolean logged;
        // se l'utente è nullo (cioè non esiste un account con questo username)
        if (utente == null) {
            logged = false;
            System.out.println("ciao");
            resp.sendRedirect("loginError.html");
        }
        else {
            // se le credenziali sono entrambe giuste
            if (password.equals(utente.getPassword())) {
                logged = true;
                HttpSession session = req.getSession();

                // scrivo nella sessione l'utente che ha fatto login e l'id della sessione stessa,
                // per poi passarle alla HomeServlet
                session.setAttribute("user", utente);
                session.setAttribute("sessionId", session.getId());

                // registro la session corrente nella mappa di tutte le sessioni
                req.getServletContext().setAttribute(session.getId(), session);
            }
            // se username giusto ma password sbagliata
            else {
                resp.sendRedirect("loginError.html");
                logged = false;
            }
        }

        // se loggato
        if (logged) {
            System.out.println("logged as " + username);
            /*
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/index.html");
            dispatcher.forward(req, resp);
             */
            resp.sendRedirect("");  // 2. faccio una redirect verso la homePage (HomeServlet)
        }
        /*
        else {
            System.out.println("Couldn't log in");
            // resp.sendRedirect("/notAuthorized.html");
        }
         */

    }
}
