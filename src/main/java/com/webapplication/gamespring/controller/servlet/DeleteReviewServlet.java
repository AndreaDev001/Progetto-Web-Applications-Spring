package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteReview")
public class DeleteReviewServlet extends HttpServlet {

    /**
     *
     * Invocata quando si vuole eliminare una recensione dal database, oltre
     * all'eliminazione di quest'ultima viene anche bannato l'utente
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String review = req.getParameter("recensione");
        String user = req.getParameter("recUt");
        int recensione2 = Integer.parseInt(review);
        //elimino la recensione dal database e banno l'utente
        DatabaseManager.getInstance().getRecensioneDao().delete(recensione2);
        Utente uDao = DatabaseManager.getInstance().getUtenteDao().findByPrimaryKey(user);
        uDao.setBandito(true);
        DatabaseManager.getInstance().getUtenteDao().saveOrUpdate(uDao);
        resp.sendRedirect("http://localhost:8080/reports");
    }
}