package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteReports")
public class DeleteReportsServlet extends HttpServlet {

    /***
     *
     * Invocata per eliminare una segnalazione
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String review = req.getParameter("recensione");
        String user = req.getParameter("utente");
        int intReview = Integer.parseInt(review);
        DatabaseManager.getInstance().getSegnalazioneDao().delete(intReview, user);
        resp.sendRedirect("http://localhost:8080/reports");
    }
}