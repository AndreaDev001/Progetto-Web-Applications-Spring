package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.model.dto.UtenteDto;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class Authentication {
    @GetMapping("/checkAuth")
    public Boolean isAuth(HttpServletRequest req, String jsessionid) {
        System.out.println("Sono in Auth rest controller con jsessionid = " + jsessionid);  // todo: debug
        // ricevo la jessionid dal chiamante (service di Angular) e controllo che
        // esista un obj session avente come sessionid = quella passata dal client
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        if (session != null) {
            System.out.println("user: " + session.getAttribute("user"));  // todo: debug
            return true;
        }
        else {
            return false;
        }
    }
    @GetMapping("/getUser")
    public UtenteDto getUtente(HttpServletRequest request){
        String jsessionid = request.getQueryString().split("=")[1];
        Utente result = null;
        HttpSession session = (HttpSession)request.getServletContext().getAttribute(jsessionid);
        if(session != null)
            result = (Utente)session.getAttribute("user");
        return result != null ? new UtenteDto(result) : null;
    }
    @PostMapping("/doLogin")
    public String[] doLogin(HttpServletRequest req){
        String[] results = new String[2];
        String[] values = req.getQueryString().split("&");
        String username = values[0].split("=")[1];
        String password = values[1].split("=")[1];
        if(!username.isEmpty() && !password.isEmpty()){
            UtenteDao utenteDao = DatabaseManager.getInstance().getUtenteDao();
            Utente utente = utenteDao.findByPrimaryKey(username);
            if(utente != null){
                HttpSession session = req.getSession();
                session.setAttribute("user",utente);
                session.setAttribute("sessionId",session.getId());
                session.getServletContext().setAttribute(session.getId(),session);
                results[0] = session.getId();
                results[1] = utente.getUsername();
            }
        }
        return results;
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.removeAttribute("sessionId");
        session.removeAttribute("user");
        session.invalidate();
        System.out.println("Logout: sessionsID: " + session);
    }
}
