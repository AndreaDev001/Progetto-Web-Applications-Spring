package com.webapplication.gamespring.util;

public class ValidationHandler {
    private ValidationHandler() {}
    private static ValidationHandler instance = null;
    public static ValidationHandler getInstance() {
        if (instance == null)
            instance = new ValidationHandler();
        return instance;
    }

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

    public boolean validateUsername(String username) {
        return (username.length() >= 3 && username.length() <= 50);
    }

    public boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z\\d_+&*-\\/]+(?:\\.[a-zA-Z\\d_+&*-]+)*@(?:[a-zA-Z\\d-]+\\.)+[a-zA-Z]{2,7}$");
    }
}
