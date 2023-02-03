package com.webapplication.gamespring.util;

/**
 * Singleton il cui compito è quello di validare i campi di username, password
 * e mail riempiti dall'utente in fase di autenticazione o modifica password.
 */
public class ValidationHandler {
    private ValidationHandler() {}
    private static ValidationHandler instance = null;
    public static ValidationHandler getInstance() {
        if (instance == null)
            instance = new ValidationHandler();
        return instance;
    }

    /**
     * Controlla che la password sia valida.
     * Una password è valida se: contiene almeno una lettera minuscola,
     * contiene almeno una lettera maiuscola, contiene almeno un numero,
     * contiene almeno un carattere speciale, non contiene spazi
     * ed è composta da almeno 8 caratteri.
     *
     * @param password la password da validare
     * @return true se la password è valida
     * @return false se lo username non è valido
     */
    public boolean validatePassword(String password) {
        if (password.length() < 8 ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*\\d.*") ||
                !password.matches(".*[^\\s\\d\\w].*") ||
                password.contains(" "))
            return false;
        return true;
    }

    /**
     * Controlla che lo username sia valido.
     * Uno username è valido se è composto da minimo 3 e massimo 50 caratteri.
     *
     * @param username lo username da validare
     * @return true se lo username è valido
     * @return false se lo username non è valido
     */
    public boolean validateUsername(String username) {
        return (username.length() >= 3 && username.length() <= 50);
    }

    /**
     * Controlla che l'email sia valida.
     * Un'email è valida rispetta l'espressione regolare seguente.
     *
     * @param email l'email da validare
     * @return true se l'email è valida
     * @return false se l'email non è valida
     */
    public boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z\\d_+&*-\\/]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$");
    }
}
