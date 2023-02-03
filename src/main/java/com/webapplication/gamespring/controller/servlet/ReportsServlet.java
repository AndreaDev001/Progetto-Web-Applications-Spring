package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.model.Segnalazione;
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
import java.util.List;


@WebServlet("/reports")
public class ReportsServlet extends HttpServlet{

    /**
     *
     * Viene invocata quando si vuole accedere alla pagina amministrativa dedicata
     * al controllo delle segnalazioni, da qui l'utente admini può eventualmente eliminare
     * una recensione o eliminare una segnalazione
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
        List<Segnalazione> reports = DatabaseManager.getInstance().getSegnalazioneDao().findAll();
        req.setAttribute("recensioni_segnalate", reports);
        RequestDispatcher dispacher = req.getRequestDispatcher("views/report.html");
        dispacher.forward(req, resp);
    }
}
