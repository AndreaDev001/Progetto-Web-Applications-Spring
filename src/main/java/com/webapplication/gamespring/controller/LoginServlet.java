package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet ("/login")
public class LoginServlet extends HttpServlet {

    // invocata da index.html (GET)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("emptyFields", false);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/login.html");
        dispatcher.forward(req, resp);
    }

    // invocata dal bottone login quando si invia il login (form in login.html, action = POST)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("sono in loginServlet");  // todo: debug
        if (username.isEmpty() || username.isBlank() || password.isEmpty() || password.isBlank()) {
            System.out.println("ciaoooooooooo"); // todo: leva

            // resp.sendRedirect("loginError.html");
            HttpSession session = req.getSession();
            session.setAttribute("emptyFields", true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/login.html");
            dispatcher.forward(req, resp);
        }

        else {
            UtenteDao udao = DatabaseManager.getInstance().getUtenteDao();
            Utente utente = udao.findByPrimaryKey(username);

            boolean logged;
            // se l'utente è nullo (cioè non esiste un account con questo username)
            if (utente == null) {
                logged = false;
                resp.sendRedirect("loginError.html");
            }
            else {
                // se le credenziali sono entrambe giuste
                if (BCrypt.checkpw(password, utente.getPassword())) {
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

                resp.sendRedirect("");  // 2. faccio una redirect verso la homePage (HomeServlet)
            }

        }



    }
}
