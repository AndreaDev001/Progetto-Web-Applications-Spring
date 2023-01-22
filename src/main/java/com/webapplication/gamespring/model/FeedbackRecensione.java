package com.webapplication.gamespring.model;

public class FeedbackRecensione
{
    private String utente;
    private int recensione;
    private boolean tipo;

    public FeedbackRecensione()
    {

    }
    public FeedbackRecensione(String utente,int recensione,boolean tipo){
        this.utente = utente;
        this.recensione = recensione;
        this.tipo = tipo;
    }
    public FeedbackRecensione(FeedbackRecensione other){
        this.utente = other.utente;
        this.recensione = other.recensione;
        this.tipo = other.tipo;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof FeedbackRecensione feedbackRecensione))
            return false;
        return this.utente.equals(feedbackRecensione.utente) && this.recensione == feedbackRecensione.getRecensione();
    }
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
