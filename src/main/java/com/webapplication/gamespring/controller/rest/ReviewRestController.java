package com.webapplication.gamespring.controller.rest;

import com.webapplication.gamespring.model.FeedbackRecensione;
import com.webapplication.gamespring.model.Recensione;
import com.webapplication.gamespring.model.Segnalazione;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.persistenza.Dao.RecensioneDao;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReviewRestController {

    @GetMapping(value = "/getReviews")
    public List<Recensione> getReviews(@RequestParam int gameID)
    {
        return DatabaseManager.getInstance().getRecensioneDao().getGameReviews(gameID);
    }

    /**
     * @return la recensione ed il corrispettivo feedback lasciato dall'utente associato con la jsessionid
     */
    @GetMapping(value = "/getReview")
    public Map<String, Object> getReview(HttpServletRequest req, @RequestParam int reviewID, @RequestParam(required = false) String jsessionid) throws SQLException {

        HttpSession session = jsessionid == null ? null : (HttpSession)req.getServletContext().getAttribute(jsessionid);
        Utente utente = session != null ? (Utente) session.getAttribute("user") : null;

        Map<String, Object> map = new HashMap<>();
        map.put("review", DatabaseManager.getInstance().getRecensioneDao().findByPrimaryKey(reviewID));

        if(utente != null) {
            FeedbackRecensione feed = DatabaseManager.getInstance().getFeedbackRecensioneDao().findByPrimaryKey(utente.getUsername(), reviewID);
            map.put("feedback", feed == null ? "none" : (feed.isTipo() ? "like" : "dislike"));
        }
        else
            map.put("feedback", "none");
        return map;
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
    public boolean editReview(@RequestBody Recensione review) throws IllegalArgumentException, SQLException {
        isReviewValid(review);
        return DatabaseManager.getInstance().getRecensioneDao().update(review);
    }

    @DeleteMapping(value = "/deleteReview/{reviewID}")
    public void deleteReview(@PathVariable int reviewID) throws IllegalArgumentException,SQLException {
        DatabaseManager.getInstance().getRecensioneDao().delete(reviewID);
    }

    @PostMapping(value = "/reportReview")
    public void reportReview(@RequestBody Segnalazione segnalazione) throws SQLException, IllegalArgumentException {
        DatabaseManager.getInstance().getSegnalazioneDao().save(segnalazione);
    }

    @PostMapping(value = "/changeReviewFeedback")
    public String changeFeedback(@RequestBody FeedbackRecensione feedbackRecensione) throws SQLException {
        if(DatabaseManager.getInstance().getFeedbackRecensioneDao().saveOrUpdate(feedbackRecensione))
            return feedbackRecensione.isTipo() ? "like" : "dislike";
        return "none";
    }

    /**
     * @param review la recensione da controllare
     * @throws IllegalArgumentException lanciato quando non valido.
     */
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

    /**
     * @return il messaggio da far ricevere al client quando la richiesta è invalida
     */
    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String failedServetParameter(Exception ex) {
        return ex.getMessage();
    }


    /**
     * @return codice di errore specifico di sql
     * (sicurezza da rivedere data l'informazione data ad un client potenzialmente malevolo
     */
    @ResponseBody
    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String,String> invalidData(SQLException ex) {
        Map<String, String> result = new HashMap<>();
        result.put("sqlError", ex.getSQLState());
        return result;
    }
}
