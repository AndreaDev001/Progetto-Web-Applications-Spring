package com.webapplication.gamespring.controller.rest;

import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.persistenza.Dao.GiocoDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameRestController
{
    /***
     * Verifica se un gioco è contenuto nel database
     * @param gameID Il gioco da cercare
     * @return Ritorna se il gioco è contenuto o meno
     */
    @GetMapping("containsGame")
    public boolean containsGame(@RequestParam int gameID){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        return giocoDao.alreadyInDatabase(gameID);
    }

    /***
     * Prende un gioco dal database
     * @param gameID L'id del gioco da prendere
     * @return Il gioco avente l'id richiesto
     */
    @GetMapping("getGame")
    public Gioco getGame(@RequestParam int gameID){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        return giocoDao.findByPrimaryKey(gameID);
    }

    /***
     * Aggiunge un gioco al database
     * @param gameID L'id del gioco
     * @param genere Il genere del gioco
     * @param titolo Il titolo del gioco
     * @param immagine L'immagine del gioco
     */
    @PostMapping("addGame")
    public void addGame(@RequestParam int gameID,@RequestParam String genere, @RequestParam String titolo, @RequestParam String immagine){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        Gioco gioco = new Gioco();
        gioco.setId(gameID);
        gioco.setGenere(genere);
        gioco.setTitolo(titolo);
        gioco.setImmagine(immagine);
        giocoDao.saveOrUpdate(gioco);
    }

    /***
     * Rimuove un gioco dal database
     * @param gameID L'id del gioco da rimuovere
     */
    @PostMapping("removeGame")
    public void removeGame(@RequestParam int gameID){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        Gioco gioco = new Gioco();
        gioco.setId(gameID);
        giocoDao.delete(gioco);
    }
}
