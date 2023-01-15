package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Segnalazione;
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
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet("/applyModify")
public class ApplyModify extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String admin= req.getParameter("admin");
        String ban = req.getParameter("ban");

        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        Utente u = new Utente();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(encryptedPassword);

        if(admin == null){
            u.setAmministratore(false);
        }
        else
            u.setAmministratore(true);

        if(ban == null){
            u.setBandito(false);
        }
        else
            u.setBandito(true);

        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(u);

            List<Utente> userList = DatabaseManager.getInstance().getUtenteDao().findAll();

            req.setAttribute("lista_utenti", userList);

            RequestDispatcher dispacher = req.getRequestDispatcher("views/userList.html");
            dispacher.forward(req, resp);


    }
}
