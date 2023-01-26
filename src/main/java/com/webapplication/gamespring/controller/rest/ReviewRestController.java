package com.webapplication.gamespring.controller.rest;

import com.webapplication.gamespring.model.FeedbackRecensione;
import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
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
    @GetMapping(value = "/getUserReviews")
    public List<Recensione> getUserReviews(@RequestParam String username){
        RecensioneDao recensioneDao = DatabaseManager.getInstance().getRecensioneDao();
        return recensioneDao.getUserReviews(username);
    }
    @PostMapping(value = "/publishReview")
    public int publishReview(@RequestBody Recensione review) throws IllegalArgumentException, SQLException {
        isReviewValid((review));
        return DatabaseManager.getInstance().getRecensioneDao().save(review);
    }

    @PostMapping(value = "/editReview")
    public boolean editReview(@RequestBody Recensione review) throws IllegalArgumentException {
        isReviewValid(review);
        return DatabaseManager.getInstance().getRecensioneDao().update(review);
    }

    @DeleteMapping(value = "/deleteReview/{reviewID}")
    public void deleteReview(@PathVariable int reviewID) throws IllegalArgumentException {
        DatabaseManager.getInstance().getRecensioneDao().delete(reviewID);
    }

    @PostMapping(value = "/reportReview")
    public void reportReview(@RequestBody Segnalazione segnalazione) throws IllegalArgumentException {
        DatabaseManager.getInstance().getSegnalazioneDao().saveOrUpdate(segnalazione);
    }

    @PostMapping(value = "/changeReviewFeedback")
    public String changeFeedback(@RequestBody FeedbackRecensione feedbackRecensione) throws SQLException {
        if(DatabaseManager.getInstance().getFeedbackRecensioneDao().saveOrUpdate(feedbackRecensione))
            return feedbackRecensione.isTipo() ? "like" : "dislike";
        return "none";
    }

    @GetMapping(value = "/getReviewFeedback")
    public String getReviewFeedback(@RequestParam String user, @RequestParam int reviewID) throws SQLException {
        FeedbackRecensione feed = DatabaseManager.getInstance().getFeedbackRecensioneDao().findByPrimaryKey(user, reviewID);
        if(feed == null)
            return "none";
        return feed.isTipo() ? "like" : "dislike";
    }

    private void isReviewValid(Recensione review) throws IllegalArgumentException
    {
        if(review.getTitolo().isEmpty())
            throw new IllegalArgumentException("Must Have Title");
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
