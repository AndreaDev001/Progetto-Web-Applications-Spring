package com.webapplication.gamespring.model;

import java.time.OffsetDateTime;

public class Commento
{
    private int id;
    private String contenuto;
    private int numeroMiPiace;
    private int numeroNonMiPiace;
    private int recensione;
    private String utente;
    private OffsetDateTime data;

    public Commento()
    {

    }
    public Commento(int id,String contenuto,int numeroMiPiace,int numeroNonMiPiace,int recensione,String utente,OffsetDateTime data){
        this.id = id;
        this.contenuto = contenuto;
        this.numeroMiPiace = numeroMiPiace;
        this.numeroNonMiPiace = numeroNonMiPiace;
        this.recensione = recensione;
        this.utente = utente;
        this.data = data;
    }
    public Commento(Commento other){
        this.id = other.id;
        this.contenuto = other.contenuto;
        this.numeroMiPiace = other.numeroMiPiace;
        this.numeroNonMiPiace = other.numeroNonMiPiace;
        this.recensione = other.recensione;
        this.utente = other.utente;
        this.data = other.data;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Commento commento))
            return false;
        return this.id == commento.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }
    public void setNumeroMiPiace(int numeroMiPiace) {
        this.numeroMiPiace = numeroMiPiace;
    }
    public void setNumeroNonMiPiace(int numeroNonMiPiace) {
        this.numeroNonMiPiace = numeroNonMiPiace;
    }
    public void setRecensione(int recensione) {
        this.recensione = recensione;
    }
    public void setUtente(String utente) {
        this.utente = utente;
    }
    public void setData(OffsetDateTime data) {
        this.data = data;
    }
    public int getId() {return id;}
    public String getContenuto() {return contenuto;}
    public int getNumeroMiPiace() {return numeroMiPiace;}
    public int getNumeroNonMiPiace() {return numeroNonMiPiace;}
    public int getRecensione() {return recensione;};
    public String getUtente() {return utente;}
    public OffsetDateTime getData() {return data;}

}
