package com.webapplication.gamespring.model;

public class Segnalazione
{
    private Recensione recensione;
    private String utente;
    private String motivazione;

    public Segnalazione()
    {

    }
    public Segnalazione(Recensione recensione,String utente,String motivazione){
        this.recensione = recensione;
        this.utente = utente;
        this.motivazione = motivazione;
    }
    public Segnalazione(Segnalazione other){
        this.recensione = other.recensione;
        this.utente = other.utente;
        this.motivazione = other.motivazione;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Segnalazione segnalazione))
            return false;
        return this.recensione.getId() == segnalazione.getRecensione().getId() && this.utente.equals(segnalazione.utente);
    }
    public Recensione getRecensione() {
        return recensione;
    }

    public void setRecensione(Recensione recensione) {
        this.recensione = recensione;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }
}
