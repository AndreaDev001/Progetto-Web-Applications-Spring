package com.webapplication.gamespring.controller.rest;

import com.webapplication.gamespring.model.Gioco;
import com.webapplication.gamespring.persistenza.Dao.GiocoDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameRestController {
    @GetMapping("containsGame")
    public boolean containsGame(@RequestParam int gameID){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        return giocoDao.alreadyInDatabase(gameID);
    }
    @GetMapping("getGame")
    public Gioco getGame(@RequestParam int gameID){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        return giocoDao.findByPrimaryKey(gameID);
    }
    @PostMapping("addGame")
    public void addGame(@RequestParam int gameID,@RequestParam String genere, @RequestParam String titolo, @RequestParam String immagine){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        Gioco gioco = new Gioco();
        gioco.setId(gameID);
        gioco.setGenere(genere);
        gioco.setTitolo(titolo);
        gioco.setImmagine(immagine);
        System.out.println(immagine);
        giocoDao.saveOrUpdate(gioco);
    }
    @PostMapping("removeGame")
    public void removeGame(@RequestParam int gameID){
        GiocoDao giocoDao = DatabaseManager.getInstance().getGiocoDao();
        Gioco gioco = new Gioco();
        gioco.setId(gameID);
        giocoDao.delete(gioco);
    }
}
