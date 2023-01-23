package com.webapplication.gamespring.model;

public class Utente
{
    private String email;
    private String username;
    private String password;
    private boolean amministratore;
    private boolean bandito;

    public Utente()
    {

    }
    public Utente(String username,String email,String password,boolean amministratore,boolean bandito){
        this.email = email;
        this.username = username;
        this.password = password;
        this.amministratore = amministratore;
        this.bandito = bandito;
    }
    public Utente(Utente other){
        this.email = other.email;
        this.username = other.username;
        this.password = other.password;
        this.amministratore = other.amministratore;
        this.bandito = other.bandito;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Utente utente))
            return false;
        return this.username.equals(utente.username);
    }
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
