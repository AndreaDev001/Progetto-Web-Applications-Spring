package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class RegistrationHandler {
    @PostMapping("/doRegistration")
    public String doRegistration(@RequestBody HashMap<String, String> body) {
        String email = body.get("email");
        String username =  body.get("username");
        String password =  body.get("password");

        // todo: rifai validazione params lato server
        // email regex
        // username min 3 max 50 char
        // pw con min 1 numero, 1 minuscola, 1 maiuscola,  char speciale, min 8 char di lunghezza

        // todo: trim email e username
        email = email.trim();
        username = username.trim();

        // todo: check username inesistente nel db
        UtenteDao uDao = DatabaseManager.getInstance().getUtenteDao();
        Utente utente = uDao.findByPrimaryKey(username);
        // se esiste già un utente con questo username nel DB:
        if (utente != null) {
            return "invalidUsername";
        }

        // todo: check email inesistente nel db
        List<Utente> utenti = uDao.findAll();
        for (Utente u : utenti) {
            if (u.getEmail().equals(email))
                return "invalidEmail";
        }

        // todo: cripta pw

        // todo: crea utente e salvalo nel db
        Utente newUtente = new Utente();
        newUtente.setEmail(email);
        newUtente.setUsername(username);
        newUtente.setPassword(password);  // todo: cripta pw
        newUtente.setAmministratore(false);
        newUtente.setBandito(false);
        uDao.saveOrUpdate(newUtente);

        System.out.println("\n\nUTENTE OGGETTO:" +
                "\nusername: " + newUtente.getUsername() +
                "\nemail: " + newUtente.getEmail() +
                "\npassword: " + newUtente.getPassword());

        return "ok";
    }
}
