package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.controller.Status;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import com.webapplication.gamespring.util.MailHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/recoverAccount")
public class RecoverAccountServlet extends HttpServlet {

    /**
     * Inoltra la risorsa recoverAccount.html
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/recoverAccount.html");
        dispatcher.forward(req, resp);
    }

    /**
     * Invocata quando l'utente chiede di reimpostare la propria password.
     * Controlla che il campo 'username', passato nel body della richiesta POST,
     * corrisponda ad un utente registrato nel DB e invia una mail all'indirizzo mail associato.
     * Aggiorna gli attributi 'status' e 'user' della sessione di conseguenza.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if (username.isEmpty() || username.isBlank()) {
            HttpSession session = req.getSession();
            session.setAttribute("status", Status.EMPTY_FIELDS);

            RequestDispatcher dispatcher = req.getRequestDispatcher("views/recoverAccount.html");
            dispatcher.forward(req, resp);
        }
        else {
            UtenteDao uDao = DatabaseManager.getInstance().getUtenteDao();
            Utente utente = uDao.findByPrimaryKey(username);

            // Non esiste account associato a questo utente
            if (utente == null) {
                HttpSession session = req.getSession();
                session.setAttribute("status", Status.NONEXISTENT_ACCOUNT);


                RequestDispatcher dispatcher = req.getRequestDispatcher("views/recoverAccount.html");
                dispatcher.forward(req, resp);
            }
            // Esiste account associato a questo utente
            else {

                // cerco l'email associata a username nel db
                String email = utente.getEmail();

                // se l'invio dell'email è andato a buon fine
                if (MailHandler.getInstance().sendEmail(email)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("status", Status.SUCCESS);
                    session.setAttribute("user", utente);
                }
                else {
                    HttpSession session = req.getSession();
                    session.setAttribute("status", Status.COULDNT_SEND_EMAIL);
                    session.setAttribute("user", utente);
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("views/recoverAccount.html");
                dispatcher.forward(req, resp);
            }
        }
    }
}
