package com.webapplication.gamespring.controller.rest;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.model.dto.UtenteDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationRestController {

    /**
     * Invocato qunado si sta passando da Angular a Spring per controllare se l'utente si è autenticato
     *
     * @param req
     * @param jsessionid ID della sessione corrente
     * @return true se l'utente si è autenticato, false altrimenti
     */
    @GetMapping("/checkAuth")
    public Boolean isAuth(HttpServletRequest req, String jsessionid) {
        // ricevo la jessionid dal chiamante (service di Angular) e controllo che
        // esista un obj session avente come sessionid = quella passata dal client
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        if (session != null) {
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
    @GetMapping("/performLogout")
    public void performLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsessionid = req.getQueryString().split("=")[1];
        HttpSession session = (HttpSession)req.getServletContext().getAttribute(jsessionid);
        if(session != null){
            session.removeAttribute("user");
            session.removeAttribute("sessionId");
            session.invalidate();
        }
    }
}
