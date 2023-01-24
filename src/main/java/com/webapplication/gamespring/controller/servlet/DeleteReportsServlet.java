package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/deleteReports")
public class DeleteReportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String review = req.getParameter("recensione");
        String user = req.getParameter("utente");

        int intReview = Integer.parseInt(review);


        DatabaseManager.getInstance().getSegnalazioneDao().delete(intReview, user);

        List<Segnalazione> reports = DatabaseManager.getInstance().getSegnalazioneDao().findAll();

        req.setAttribute("recensioni_segnalate", reports);

        RequestDispatcher dispacher = req.getRequestDispatcher("views/report.html");
        dispacher.forward(req, resp);


    }
}