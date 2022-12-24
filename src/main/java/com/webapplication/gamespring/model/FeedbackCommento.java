package com.webapplication.gamespring.model;

public class FeedbackCommento {
    String utente;
    int commento;
    boolean tipo;

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getCommento() {
        return commento;
    }

    public void setCommento(int commento) {
        this.commento = commento;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
}
