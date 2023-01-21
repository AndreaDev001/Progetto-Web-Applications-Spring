package com.webapplication.gamespring.controller;


import com.webapplication.gamespring.model.Commento;
import com.webapplication.gamespring.model.FeedbackCommento;
import com.webapplication.gamespring.persistenza.DatabaseManager;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentRestController {

    @GetMapping(value = "/getComments")
    public List<Commento> getComments(@RequestParam int reviewID, @RequestParam int startIndex, @RequestParam int commentsSize)
    {
        return DatabaseManager.getInstance().getCommentoDao().getReviewComments(reviewID, startIndex, commentsSize);
    }

    @PostMapping(value = "/changeFeedback")
    public String changeFeedback(@RequestBody FeedbackCommento feedbackCommento)
    {
        if(DatabaseManager.getInstance().getFeedbackCommentoDao().saveOrUpdate(feedbackCommento))
            return feedbackCommento.isTipo() ? "like" : "dislike";
        return "none";
    }


    @GetMapping(value = "/getCommentFeedback")
    public String getCommentFeedback(@RequestParam int commentID)
    {
        FeedbackCommento feed = DatabaseManager.getInstance().getFeedbackCommentoDao().findByPrimaryKey("Pie_Oxx", commentID);
        if(feed == null)
            return "none";
        return feed.isTipo() ? "like" : "dislike";
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
