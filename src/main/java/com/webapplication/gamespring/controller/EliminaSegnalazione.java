package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminaSegnalazione")
public class EliminaSegnalazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String recensione = req.getParameter("recensione");
        String utente = req.getParameter("utente");

        int recensione2 = Integer.parseInt(recensione);


        DatabaseManager.getInstance().getSegnalazioneDao().delete(recensione2, utente);

        List<Segnalazione> segnalazioni = DatabaseManager.getInstance().getSegnalazioneDao().findAll();

        req.setAttribute("recensioni_segnalate", segnalazioni);

        RequestDispatcher dispacher = req.getRequestDispatcher("views/recensioni.html");
        dispacher.forward(req, resp);


    }
}