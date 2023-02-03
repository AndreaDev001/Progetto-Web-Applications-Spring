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

import java.io.IOException;
import java.util.List;

@WebServlet("/deleteReview")
public class DeleteReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String review = req.getParameter("recensione");
        String user = req.getParameter("utente");
        int recensione2 = Integer.parseInt(review);
        //elimino la recensione dal database e banno l'utente
        DatabaseManager.getInstance().getRecensioneDao().delete(recensione2);
        Utente uDao = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(user);
        uDao.setBandito(true);
        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(uDao);
        /**
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/report.html");
        dispatcher.forward(req, resp);
         **/
        resp.sendRedirect("http://localhost:8080/reports");
    }
}