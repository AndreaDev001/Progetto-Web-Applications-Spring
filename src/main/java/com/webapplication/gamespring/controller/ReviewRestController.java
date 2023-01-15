package com.webapplication.gamespring.controller;

import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReviewRestController {

    @GetMapping(value = "/getReviews")
    public List<Recensione> getReviews(@RequestParam int gameID)
    {
        return DatabaseManager.getInstance().getRecensioneDao().getGameReviews(gameID);
    }

    @GetMapping(value = "/getReview")
    public Recensione getReview(@RequestParam int reviewID)
    {
        return DatabaseManager.getInstance().getRecensioneDao().findByPrimaryKey(reviewID);
    }
    @GetMapping(value = "/getUserReview")
    public Recensione getUserReview(@RequestParam String username,@RequestParam int gameID)
    {
        RecensioneDao recensioneDao = DatabaseManager.getInstance().getRecensioneDao();
        return recensioneDao.getUserReview(username,gameID);
    }
    @PostMapping(value = "/publishReview")
    public int publishReview(@RequestBody Recensione review) throws IllegalArgumentException {


        review.setUtente("pier");
        review.setGioco(11);
        isReviewValid((review));
        return DatabaseManager.getInstance().getRecensioneDao().save(review);
    }

    @PostMapping(value = "/editReview")
    public boolean editReview(@RequestBody Recensione review) throws IllegalArgumentException {
        isReviewValid(review);
        return DatabaseManager.getInstance().getRecensioneDao().update(review);
    }

    private void isReviewValid(Recensione review) throws IllegalArgumentException
    {
        if(review.getTitolo().length() > 20)
            throw new IllegalArgumentException("Invalid Title Length");
        if(review.getVoto() < 0 || review.getVoto() > 10)
            throw new IllegalArgumentException("Invalid Vote");

        String formattedHtml = Jsoup.parse(review.getContenuto()).text();
        //minimum 50 characters
        if(formattedHtml.length() < 50)
            throw new IllegalArgumentException("Invalid HTML Length");


    }


    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String failedServetParameter(Exception ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({SQLException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String failedQuery(Exception ex) {
        return ex.getMessage();
    }

}
