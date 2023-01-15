package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Wishlist;
import com.webapplication.gamespring.persistenza.Dao.WishlistDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WishlistController
{
    @GetMapping("/containsGameWishlist")
    public boolean containsGame(@RequestParam String username,@RequestParam int gameID){
        WishlistDao wishlistDao = DatabaseManager.getInstance().getWishlistDao();
        return wishlistDao.alreadyInDatabase(gameID,username);
    }
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
}
