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
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

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


        UtenteDao uDao = DatabaseManager.getInstance().getUtenteDao();

        // check email inesistente nel db
        List<Utente> utenti = uDao.findAll();
        for (Utente ut : utenti) {
            if (ut.getEmail().equals(email)){

                RequestDispatcher dispacher = req.getRequestDispatcher("views/errorModifyProfile.html");
                dispacher.forward(req, resp);
                break;
            }

        }

        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(u);

            List<Utente> userList = DatabaseManager.getInstance().getUtenteDao().findAll();

            req.setAttribute("lista_utenti", userList);

            RequestDispatcher dispacher = req.getRequestDispatcher("views/userList.html");
            dispacher.forward(req, resp);


    }
}
