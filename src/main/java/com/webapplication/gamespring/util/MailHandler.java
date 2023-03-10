package com.webapplication.gamespring.util;



import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

/**
 * Singleton utilizzata dalla Servlet RecoverAccountServlet per inviare
 * all'utente una mail per modificare la propria password
 */
public class MailHandler {

    private MailHandler() {
        config();
    }
    private static MailHandler instance = null;
    public static MailHandler getInstance() {
        if (instance == null)
            instance = new MailHandler();
        return instance;
    }

    private final String fromEmail = "progettoweb2022@libero.it";
    private final String host = "smtp.libero.it";
    private final Properties properties = System.getProperties();
    private Session session;


    /**
     * Configura alcune proprietà di sistema riguradanti il protocollo smtp e
     * esegue l'autenticazione dell'account mail this.fromMail.
     */
    private void config() {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, "progettoWeb22-");
            }
        });
    }

    /**
     * Invia un email contenente un link per modificare la propria password dall'account this.fromMail.
     *
     * @param toEmail indirizzo email di destinazione
     * @return true se l'email è stata inviata con successo, false altrimenti
     */
    public boolean sendEmail(String toEmail) {

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromEmail));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            message.setSubject("Reset your Password");

            message.setContent("<p>Click <a href=\"http://localhost:8080/changePassword\">here</a> to reset your password</p>", "text/HTML");

            message.setSentDate(new Date());
            Transport.send(message);
            return true;

        } catch (MessagingException mex) {
            return false;
        }


    }

}
