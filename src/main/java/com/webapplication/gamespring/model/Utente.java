package com.webapplication.gamespring.model;

public class Utente {
    String email;
    String username;
    String password;
    boolean amministratore;
    boolean bandito;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAmministratore() {
        return amministratore;
    }

    public void setAmministratore(boolean amministratore) {
        this.amministratore = amministratore;
    }

    public boolean isBandito() {
        return bandito;
    }

    public void setBandito(boolean bandito) {
        this.bandito = bandito;
    }
}
