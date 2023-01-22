package com.webapplication.gamespring.model;

import java.time.OffsetDateTime;

public class Recensione
{
    private int id;
    private String titolo;
    private String contenuto;
    private int voto;
    private int numeroMiPiace;
    private int numeroNonMiPiace;
    private String utente;
    private int gioco;
    private OffsetDateTime data;

    public Recensione()
    {

    }
    public Recensione(int id,String titolo,String contenuto,int voto,int numeroMiPiace,int numeroNonMiPiace,String utente,int gioco,OffsetDateTime data){
     this.id = id;
     this.titolo = titolo;
     this.contenuto = contenuto;
     this.voto = voto;
     this.numeroMiPiace = numeroMiPiace;
     this.numeroNonMiPiace = numeroNonMiPiace;
     this.utente = utente;
     this.gioco = gioco;
     this.data = data;
    }
    public Recensione(Recensione other){
        this.id = other.id;
        this.titolo = other.titolo;
        this.contenuto = other.contenuto;
        this.voto = other.voto;
        this.numeroMiPiace = other.numeroMiPiace;
        this.numeroNonMiPiace = other.numeroNonMiPiace;
        this.utente = other.utente;
        this.gioco = other.gioco;
        this.data = other.data;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Recensione recensione))
            return false;
        return this.id == recensione.id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public int getNumeroMiPiace() {
        return numeroMiPiace;
    }

    public void setNumeroMiPiace(int numeroMiPiace) {
        this.numeroMiPiace = numeroMiPiace;
    }

    public int getNumeroNonMiPiace() {
        return numeroNonMiPiace;
    }

    public void setNumeroNonMiPiace(int numeroNonMiPiace) {
        this.numeroNonMiPiace = numeroNonMiPiace;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getGioco() {
        return gioco;
    }

    public void setGioco(int gioco) {
        this.gioco = gioco;
    }


    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }
}
