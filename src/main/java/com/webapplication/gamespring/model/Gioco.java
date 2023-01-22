package com.webapplication.gamespring.model;

public class Gioco
{
    private int id;
    private String genere;
    private String titolo;
    private String immagine;

    public Gioco()
    {

    }
    public Gioco(int id,String genere,String titolo,String immagine){
        this.id = id;
        this.genere = genere;
        this.titolo = titolo;
        this.immagine = immagine;
    }
    public Gioco(Gioco other){
        this.id = other.id;
        this.genere = other.genere;
        this.titolo = other.titolo;
        this.immagine = other.immagine;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(!(obj instanceof Gioco gioco))
            return false;
        return this.id == gioco.id;
    }
    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}
