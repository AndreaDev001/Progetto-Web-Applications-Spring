package com.webapplication.gamespring.model;

public class FeedbackRecensione {
    String utente;
    int recensione;
    boolean tipo;

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getRecensione() {
        return recensione;
    }

    public void setRecensione(int recensione) {
        this.recensione = recensione;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
}
