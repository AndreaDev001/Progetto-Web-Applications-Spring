package com.webapplication.gamespring.model.dto;

import com.webapplication.gamespring.model.Utente;

public class UtenteDto
{
    private final String username;
    private final boolean bandito;
    private final boolean amministratore;

    public UtenteDto(String username,boolean bandito,boolean amministratore){
        this.username = username;
        this.bandito = bandito;
        this.amministratore = amministratore;
    }
    public UtenteDto(Utente other){
        this.username = other.getUsername();
        this.bandito = other.isBandito();
        this.amministratore = other.isAmministratore();
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof UtenteDto other))
            return false;
        return this.username.equals(other.username);
    }
    public final String getUsername() {return username;}
    public boolean isBandito() {return bandito;}
    public final boolean isAmministratore() {return amministratore;}
}
