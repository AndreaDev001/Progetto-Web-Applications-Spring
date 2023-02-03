package com.webapplication.gamespring.controller;

/**
 * Enum utilizzati per indicare l'esito delle operazioni di
 * autenticazione / modifica password scrivendo nella sessione
 */
public enum Status {
    SUCCESS, NONEXISTENT_ACCOUNT, EMPTY_FIELDS, COULDNT_SEND_EMAIL,
    INVALID_PASSWORD, PASSWORD_UPDATED, SAME_PASSWORDS
}
