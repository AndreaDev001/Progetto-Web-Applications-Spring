package com.webapplication.gamespring.model;

import java.time.OffsetDateTime;

public class Recensione {
    int id;
    String titolo;
    String contenuto;
    int voto;
    int numeroMiPiace;
    int numeroNonMiPiace;
    String utente;
    int gioco;
    OffsetDateTime data;

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
