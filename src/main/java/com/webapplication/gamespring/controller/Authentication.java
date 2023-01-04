package com.webapplication.gamespring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
