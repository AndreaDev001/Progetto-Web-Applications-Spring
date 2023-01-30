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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String admin= req.getParameter("admin");
        String ban = req.getParameter("ban");

        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));


        Utente utente = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(username);

        if(!Objects.equals(utente.getEmail(), email)) {

            List<Utente> utenti = DatabaseManager.getInstance().getUtenteDao().findAll();
            for (Utente ut : utenti) {
                if (ut.getEmail().equals(email)) {

                    RequestDispatcher dispacher = req.getRequestDispatcher("views/errorModifyProfile.html");
                    dispacher.forward(req, resp);
                    break;
                }

            }

            utente.setEmail(email);

        }

        if(!password.equals("")){

            utente.setPassword(encryptedPassword);

        }

        if(admin == null){
            utente.setAmministratore(false);
        }
        else
            utente.setAmministratore(true);

        if(ban == null){
            utente.setBandito(false);
        }
        else
            utente.setBandito(true);



        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(utente);

        HttpSession session = req.getSession();

        session.setAttribute("user", utente);
        session.setAttribute("sessionId", session.getId());

        req.getServletContext().setAttribute(session.getId(), session);


        List<Utente> userList = DatabaseManager.getInstance().getUtenteDao().findAll();

        req.setAttribute("lista_utenti", userList);

        RequestDispatcher dispacher = req.getRequestDispatcher("views/userList.html");
        dispacher.forward(req, resp);


    }
}
