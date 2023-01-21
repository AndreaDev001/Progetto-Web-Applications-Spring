package com.webapplication.gamespring.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Date;

public class Commento {
    int id;
    String contenuto;
    int numeroMiPiace;
    int numeroNonMiPiace;
    int recensione;
    String utente;
    OffsetDateTime data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
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

    public int getRecensione() {
        return recensione;
    }

    public void setRecensione(int recensione) {
        this.recensione = recensione;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
    }
}
