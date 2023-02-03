package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.controller.Status;
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

    /**
     * Inoltra la risorsa changePassword.html solo se l'attributo 'user'
     * della sessione è correttamente valorizzato,
     * altrimenti nega l'accesso inoltrando la risorsa notAuthorized.html
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/notAuthorized.html");
            dispatcher.forward(req, resp);
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/changePassword.html");
            dispatcher.forward(req, resp);
        }

    }


    /**
     * Invocata quando l'utente ha impostato la nuova password, che viene passata come parametro nel body della richiesta POST.
     * Se la nuova password è valida, ne effettua l'hash e aggiorna nel DB la password dell'utente corrispondente di conseguenza.
     * Se la nuova password non è valida, annulla l'operazione.
     * In entrambi i casi, l'esito dell'operazione viene scritto nell'attributo "status" della sessione.
     *
     * @param req la richiesta POST che contiene la nuova password nel proprio body
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");

        if (password.isEmpty() || password.isBlank()) {
            HttpSession session = req.getSession();
            session.setAttribute("status", Status.EMPTY_FIELDS);

            RequestDispatcher dispatcher = req.getRequestDispatcher("views/changePassword.html");
            dispatcher.forward(req, resp);
        }

        else {
            // se pw non valida -> errore
            if (!ValidationHandler.getInstance().validatePassword(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("status", Status.INVALID_PASSWORD);

            }
            // se pw valida
            else {
                Utente utente = (Utente) req.getSession().getAttribute("user");
                UtenteDao utenteDao = DatabaseManager.getInstance().getUtenteDao();

                if (BCrypt.checkpw(password, utente.getPassword())) {
                    HttpSession session = req.getSession();
                    session.setAttribute("status", Status.SAME_PASSWORDS);
                }
                else {
                    String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

                    utente.setPassword(encryptedPassword);
                    utenteDao.saveOrUpdate(utente);

                    HttpSession session = req.getSession();
                    session.setAttribute("status", Status.PASSWORD_UPDATED);
                }

            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/changePassword.html");
            dispatcher.forward(req, resp);

        }

    }


}
