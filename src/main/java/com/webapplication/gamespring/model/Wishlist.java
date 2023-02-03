package com.webapplication.gamespring.model;

public class Wishlist
{
    private int gioco;
    private String utente;

    public Wishlist()
    {

    }
    public Wishlist(int gioco,String utente){
        this.gioco = gioco;
        this.utente = utente;
    }
    public Wishlist(Wishlist other){
        this.gioco = other.gioco;
        this.utente = other.utente;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Wishlist wishlist))
            return false;
        return this.gioco == wishlist.gioco;
    }
    public int getGioco() {
        return gioco;
    }

    public void setGioco(int gioco) {
        this.gioco = gioco;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }
}
