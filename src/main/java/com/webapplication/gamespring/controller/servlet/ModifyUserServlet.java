package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/modifyUser")
public class ModifyUserServlet extends HttpServlet {


    /**
     *
     * Invocata quando si accede alla pagina di amministrazione del singolo utente,
     * permette di visualizzare una pagina dedicata dove poter effettuare modifiche al profilo selezionato
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession httpSession = req.getSession();
        Utente utente = (Utente)httpSession.getAttribute("user");
        if(utente == null || !utente.isAmministratore() || utente.isBandito()){
            resp.sendRedirect("notPermitted");
            return;
        }


        String username = req.getParameter("username");
        //cerco tutti i dati dell'utente e carico la pagina per poter modificare l'utente
        Utente user = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(username);
        req.setAttribute("modify_utente", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/modifyUser.html");
        dispatcher.forward(req, resp);
    }
}
