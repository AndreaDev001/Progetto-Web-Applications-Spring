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

import java.io.IOException;

@WebServlet("/resetPassword")
public class resetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("emptyFields", false);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/resetPassword.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username.isEmpty() || username.isBlank()) {
            HttpSession session = req.getSession();
            /*
            session.setAttribute("emptyFields", true);
            session.setAttribute("invalidUser", false);
            session.setAttribute("ok", false);
             */
            session.setAttribute("status", Status.EMPTY_FIELDS);
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/resetPassword.html");
            dispatcher.forward(req, resp);
        }
        else {
            // todo: check che esista account associato ad email nel db
            UtenteDao uDao = DatabaseManager.getInstance().getUtenteDao();
            Utente utente = uDao.findByPrimaryKey(username);


            // Non esiste account associato a questo utente
            if (utente == null) {
                System.out.println("utente null");
                HttpSession session = req.getSession();
                /*
                session.setAttribute("invalidUser", true);
                session.setAttribute("emptyFields", false);
                session.setAttribute("ok", false);
                 */
                session.setAttribute("status", Status.INEXISTENT_ACCOUNT);

                RequestDispatcher dispatcher = req.getRequestDispatcher("views/resetPassword.html");
                dispatcher.forward(req, resp);
            }
            // Esiste account associato a questo utente
            else {
                System.out.println("username: " + utente.getUsername());
                // todo: send email
                HttpSession session = req.getSession();
                /*
                session.setAttribute("invalidUser", false);
                session.setAttribute("emptyFields", false);
                session.setAttribute("ok", true);
                 */
                session.setAttribute("status", Status.SUCCESS);

                RequestDispatcher dispatcher = req.getRequestDispatcher("views/resetPassword.html");
                dispatcher.forward(req, resp);
            }
        }
    }
}
