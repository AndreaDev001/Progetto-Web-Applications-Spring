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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String review = req.getParameter("recensione");
        String user = req.getParameter("utente");


        int recensione2 = Integer.parseInt(review);


        DatabaseManager.getInstance().getRecensioneDao().delete(recensione2);
        Utente uDao = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(user);
        uDao.setBandito(true);
        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(uDao);


        List<Segnalazione> reports = DatabaseManager.getInstance().getSegnalazioneDao().findAll();
        req.setAttribute("recensioni_segnalate", reports);
        RequestDispatcher dispacher = req.getRequestDispatcher("views/report.html");
        dispacher.forward(req, resp);


    }
}