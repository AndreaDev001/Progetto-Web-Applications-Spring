package com.webapplication.gamespring.controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String sessionId = (String) session.getAttribute("sessionId");
        String redirectTo = (sessionId != null) ? "http://localhost:4200/games?jsessionid=" + sessionId : "http://localhost:4200/games";
        resp.sendRedirect(redirectTo);
    }
}
