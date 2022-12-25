package com.webapplication.gamespring.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Servlet di prova che sostituisce la home
@WebServlet("")
public class HomeServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // 3. prendo la session che mi ha passato la login servlet
		System.out.println("sessionId:\t" + session.getAttribute("sessionId")); // todo: debug -> rimuovi

		/*
		Utente u = (Utente) session.getAttribute("user");

		if (u != null) {
			req.setAttribute("user", u);
			req.setAttribute("sessionId", session.getId());  // 4. passo il session id come parametro della request
		}
		*/


		RequestDispatcher dispacher = req.getRequestDispatcher("index.html");
		dispacher.forward(req, resp);
	}
}
