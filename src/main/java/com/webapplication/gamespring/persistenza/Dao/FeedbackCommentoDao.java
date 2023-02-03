package com.webapplication.gamespring.persistenza.Dao;

import com.webapplication.gamespring.model.FeedbackCommento;

public interface FeedbackCommentoDao {

    FeedbackCommento findByPrimaryKey(String utente, int commento);

    /**
     * questo methodo non aggiorna forzatamente il feedback dell'utente ma la modifica avviene a seconda dello stato iniziale di esso
     * es: se il feedback sul momento è un non mi piace e arriva una richiesta di non mi piace il risultato sarà che il feedback sarà eliminato
     * al contrario se un feedback per un commento non esiste, ne verrà creato uno.
     * Questo comportamento è stato implementato come procedura all'interno di Postgres
     * @param feedbackCommento identifica utente, tipo di feedback da eseguire e su quale commento
     */
    boolean saveOrUpdate(FeedbackCommento feedbackCommento);

}
