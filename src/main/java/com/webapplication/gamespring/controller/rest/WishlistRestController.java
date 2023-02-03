package com.webapplication.gamespring.controller.rest;

import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.model.Wishlist;
import com.webapplication.gamespring.persistenza.Dao.WishlistDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistRestController
{
    /***
     * Verifica se un gioco è contenuto o meno nella lista dei desideri di un utente
     * @param username L'username dell'utente
     * @param gameID L'id del gioco
     * @return Ritorna se il gioco è contenuto o meno nella lista dei desideri dell'utente
     */
    @GetMapping("/containsGameWishlist")
    public boolean containsGame(@RequestParam String username,@RequestParam int gameID){
        WishlistDao wishlistDao = DatabaseManager.getInstance().getWishlistDao();
        return wishlistDao.alreadyInDatabase(gameID,username);
    }

    /***
     * Ottiene i giochi contenuti dentro la lista dei desideri di un utente
     * @param username L'username dell'utente
     * @return La lista trovata
     */
    @GetMapping("/getWishlist")
    public List<Integer> getWishlistGames(@RequestParam String username){
        List<Integer> result = new ArrayList<>();
        WishlistDao wishlistDao = DatabaseManager.getInstance().getWishlistDao();
        List<Wishlist> wishlists = wishlistDao.findByUser(username);
        for(Wishlist current : wishlists){
            int gameID = current.getGioco();
            result.add(gameID);
        }
        return result;
    }
    /***
     * Aggiunge un gioco alla lista dei desideri di un utente
     * @param username L'username dell'utente
     * @param gameID L'id del gioco da aggiungere
     */
    @PostMapping("/addGameWishlist")
    public void addGameWishlist(@RequestParam String username,@RequestParam int gameID){
        WishlistDao wishlistDao = DatabaseManager.getInstance().getWishlistDao();
        boolean contained = wishlistDao.alreadyInDatabase(gameID,username);
        if(!contained){
            Wishlist wishlist = new Wishlist();
            wishlist.setGioco(gameID);
            wishlist.setUtente(username);
            wishlistDao.save(wishlist);
        }
    }
    /***
     * Rimuove un gioco dalla lista dei desideri di un utente
     * @param username L'username dell'utente
     * @param gameID L'id del gioco da rimuovere
     */
    @PostMapping("/removeGameWishlist")
    public void removeGameWishlist(@RequestParam String username,@RequestParam int gameID) {
        WishlistDao wishlistDao = DatabaseManager.getInstance().getWishlistDao();
        boolean contained = wishlistDao.alreadyInDatabase(gameID, username);
        if(contained){
            Wishlist wishlist = new Wishlist();
            wishlist.setUtente(username);
            wishlist.setGioco(gameID);
            wishlistDao.delete(wishlist);
        }
    }
    /***
     * Rimuove un gioco dalla lista dei desideri, quando l'utente è dentro la pagina della propria lista dei desideri
     * @param req Utilizzato per ottenere la sessione
     * @param gameID L'id del gioco da rimuovere
     */
    @PostMapping("/removeGameWishlistSpring")
    public void removeGameWishlist(HttpServletRequest req, @RequestParam int gameID) {
        Utente utente = (Utente) req.getSession().getAttribute("user");
        if(utente == null)
            return;
        WishlistDao wishlistDao = DatabaseManager.getInstance().getWishlistDao();
        Wishlist wishlist = new Wishlist();
        wishlist.setUtente(utente.getUsername());
        wishlist.setGioco(gameID);
        wishlistDao.delete(wishlist);

    }
}
