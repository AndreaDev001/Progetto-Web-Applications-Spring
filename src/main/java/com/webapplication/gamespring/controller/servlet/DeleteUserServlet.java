package com.webapplication.gamespring.controller.servlet;

import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        //elimino l'utente dal database
        DatabaseManager.getInstance().getUtenteDao().delete(username);
        resp.sendRedirect("http://localhost:8080/userList");
    }
}
