package com.webapplication.gamespring.model;

public class FeedbackCommento
{
    private String utente;
    private int commento;
    private boolean tipo;

    public FeedbackCommento()
    {

    }
    public FeedbackCommento(String utente,int commento,boolean tipo){
        this.utente = utente;
        this.commento = commento;
        this.tipo = tipo;
    }
    public FeedbackCommento(FeedbackCommento other){
        this.utente = other.utente;
        this.commento = other.commento;
        this.tipo = other.tipo;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof FeedbackCommento feedbackCommento))
            return false;
        return this.utente.equals(feedbackCommento.getUtente()) && this.commento == feedbackCommento.getCommento();
    }
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
