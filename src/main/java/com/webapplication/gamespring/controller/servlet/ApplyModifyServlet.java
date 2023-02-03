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
import java.util.List;
import java.util.Objects;

@WebServlet("/applyModify")
public class ApplyModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String admin= req.getParameter("admin");
        String ban = req.getParameter("ban");
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        Utente utente = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(username);
        //controllo se la email è stata effettivamente modificata
        if(!Objects.equals(utente.getEmail(), email)) {
            List<Utente> utenti = DatabaseManager.getInstance().getUtenteDao().findAll();
            for (Utente ut : utenti) {
                if (ut.getEmail().equals(email)) {
                    resp.sendRedirect("http://localhost:8080/errorModifyProfile");
                    return;
                }
            }
            utente.setEmail(email);
        }
        if(!password.isEmpty())
            utente.setPassword(encryptedPassword);
        utente.setAmministratore(admin != null);
        utente.setBandito(ban != null);
        //aggiorno i dati del utente nel database
        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(utente);
        //aggiorno la sessione


        HttpSession session = req.getSession();
        Utente sessionUtente = (Utente)session.getAttribute("user");

        if(Objects.equals(sessionUtente.getUsername(), username)){
            session.setAttribute("user", utente);
            session.setAttribute("sessionId", session.getId());
            req.getServletContext().setAttribute(session.getId(), session);
        }


        //dopo le modifiche ricarico la pagina con tutti gli utenti
        resp.sendRedirect("http://localhost:8080/userList");
    }
}
