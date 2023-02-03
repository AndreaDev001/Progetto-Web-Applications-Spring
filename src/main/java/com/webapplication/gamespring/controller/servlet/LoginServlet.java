package com.webapplication.gamespring.controller.servlet;

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
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

@WebServlet ("/login")
public class LoginServlet extends HttpServlet {

    /**
     * Inoltra la risorsa login.html
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("emptyFields", false);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/login.html");
        dispatcher.forward(req, resp);
    }


    /**
     * Invocata quando l'utente chiede di effettuare il login.
     * Controlla che i campi 'username' e 'password', che vengono passati
     * nel body della richiesta POST, siano validi, e aggiorna la sessione di conseguenza.
     * Se username e password sono entrambi validi, effettua l'autenticazione, valorizza l'attributo
     * 'sessionId' della sessione e reindirizza l'utente sulla pagina principale.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.isEmpty() || username.isBlank() || password.isEmpty() || password.isBlank()) {
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
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/loginError.html");
                dispatcher.forward(req, resp);
            }
            else {
                // se le credenziali sono entrambe giuste
                if (BCrypt.checkpw(password, utente.getPassword())) {
                    logged = true;
                    HttpSession session = req.getSession();
                    session.setAttribute("user", utente);
                    session.setAttribute("sessionId", session.getId());
                    req.getServletContext().setAttribute(session.getId(), session);
                }
                // se username giusto ma password sbagliata
                else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("views/loginError.html");
                    dispatcher.forward(req, resp);
                    logged = false;
                }
            }
            // se loggato
            if (logged) {
                String desiredRedirect = "http://localhost:4200/games?jsessionid=" + req.getSession().getAttribute("sessionId");
                resp.sendRedirect(desiredRedirect);
            }
        }
    }
}
