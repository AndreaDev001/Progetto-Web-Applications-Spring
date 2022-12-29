package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.UtenteDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

        // controlla che i campi NON siano vuoti
        if (email.isEmpty() || email.isBlank() ||
                username.isEmpty() || username.isBlank() ||
                password.isEmpty() || password.isBlank() ) {
            return "emptyFields";
        }

        // trim email e username
        email = email.trim();
        username = username.trim();

        // validazione params lato server
        // email regex
        if (!email.matches("^[a-zA-Z\\d_+&*-\\/]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$")) {
            System.out.println(email + " Invalid email");
            return "invalidEmail";
        }

        // username min 3 max 50 char
        if (username.length() < 3 || username.length() > 50) {
            return "invalidUsername";
        }

        // pw con min 1 numero, 1 minuscola, 1 maiuscola, char speciale, min 8 char di lunghezza
        if (password.length() < 8 ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*\\d.*") ||
                !password.matches(".*[^\\s\\d\\w].*") ||
                password.contains(" ")) {
            return "invalidPassword";
        }


        // check username inesistente nel db
        UtenteDao uDao = DatabaseManager.getInstance().getUtenteDao();
        Utente utente = uDao.findByPrimaryKey(username);
        // se esiste già un utente con questo username nel DB:
        if (utente != null) {
            return "unavailableUsername";
        }

        // check email inesistente nel db
        List<Utente> utenti = uDao.findAll();
        for (Utente u : utenti) {
            if (u.getEmail().equals(email))
                return "unavailableEmail";
        }

        // cripta pw
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        // crea utente e salvalo nel db
        Utente newUtente = new Utente();
        newUtente.setEmail(email);
        newUtente.setUsername(username);
        newUtente.setPassword(encryptedPassword);
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
