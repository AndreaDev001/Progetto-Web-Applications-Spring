package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import com.webapplication.gamespring.util.ValidationHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    // chiamata dopo il click sul link nella mail
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            System.out.println("non autorizzato al cambio pw"); // todo: debug
            // resp.sendRedirect("loginError.html");
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/loginError.html"); // todo: cambia con pag not authorized
            dispatcher.forward(req, resp);
        }
        else {
            System.out.println("autorizzato al cambio pw"); // todo: debug
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/changePassword.html");
            dispatcher.forward(req, resp);
        }

    }

    // qunado click su conferma -> aggiorna password
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");

        if (password.isEmpty() || password.isBlank()) {
            System.out.println("pw empty");
            HttpSession session = req.getSession();
            session.setAttribute("status", Status.EMPTY_FIELDS);

            RequestDispatcher dispatcher = req.getRequestDispatcher("views/changePassword.html");
            dispatcher.forward(req, resp);
        }

        else {
            // se pw non valida -> errore
            if (!ValidationHandler.getInstance().validatePassword(password)) {
                System.out.println("Invalid pw"); // todo: debug
                HttpSession session = req.getSession();
                session.setAttribute("status", Status.INVALID_PASSWORD);

            }
            // se pw valida
            else {
                Utente utente = (Utente) req.getSession().getAttribute("user");
                UtenteDao utenteDao = DatabaseManager.getInstance().getUtenteDao();

                // todo: controlla che pw vecchia e nuova non siano le stesse
                if (BCrypt.checkpw(password, utente.getPassword())) {
                    HttpSession session = req.getSession();
                    session.setAttribute("status", Status.SAME_PASSWORDS);
                    System.out.println("Pw uguale alla vecchia");  // todo: debug

                }
                else {
                    String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

                    utente.setPassword(encryptedPassword);
                    utenteDao.saveOrUpdate(utente);

                    HttpSession session = req.getSession();
                    session.setAttribute("status", Status.PASSWORD_UPDATED);

                    System.out.println("Pw modificata correttamente!");  // todo: debug
                }

            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/changePassword.html");
            dispatcher.forward(req, resp);

        }

    }


}
