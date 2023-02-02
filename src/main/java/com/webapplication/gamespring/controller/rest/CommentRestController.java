package com.webapplication.gamespring.controller.rest;

import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.model.Utente;
import com.webapplication.gamespring.model.dto.CommentDto;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentRestController {

    @GetMapping(value = "/getComments")
    public List<CommentDto> getComments(HttpServletRequest req, @RequestParam int reviewID, @RequestParam int startIndex, @RequestParam int commentsSize, @RequestParam(required = false) String jsessionid)
    {
        HttpSession session = jsessionid == null ? null : (HttpSession)req.getServletContext().getAttribute(jsessionid);
        Utente utente = session != null ? (Utente) session.getAttribute("user") : null;

        List<CommentDto> comments = new LinkedList<>();
        DatabaseManager.getInstance().getCommentoDao().getReviewComments(reviewID, startIndex, commentsSize).forEach(comment -> {

            FeedbackCommento feed = utente != null ? DatabaseManager.getInstance().getFeedbackCommentoDao().findByPrimaryKey(utente.getUsername(), comment.getId()) : null;
            comments.add(new CommentDto(comment, feed == null ? "none" : (feed.isTipo() ? "like"  : "dislike")));
        });
        return comments;
    }

    @PostMapping(value = "/changeFeedback")
    public String changeFeedback(@RequestBody FeedbackCommento feedbackCommento)
    {
        if(DatabaseManager.getInstance().getFeedbackCommentoDao().saveOrUpdate(feedbackCommento))
            return feedbackCommento.isTipo() ? "like" : "dislike";
        return "none";
    }

    @PostMapping(value = "/addComment")
    public int addComment(@RequestBody Commento commento) throws SQLException {
        return DatabaseManager.getInstance().getCommentoDao().save(commento);
    }

    @PostMapping(value = "/editComment")
    public void editComment(@RequestBody Commento commento) throws SQLException {
        DatabaseManager.getInstance().getCommentoDao().update(commento);
    }

    @DeleteMapping(value = "/deleteComment/{commentID}")
    public void deleteComment(@PathVariable int commentID) throws SQLException {
        Commento commento = new Commento();
        commento.setId(commentID);
        DatabaseManager.getInstance().getCommentoDao().delete(commento);
    }



    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String failedServetParameter(Exception ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String failedQuery(SQLException ex) {
        return ex.getMessage();
    }
}
