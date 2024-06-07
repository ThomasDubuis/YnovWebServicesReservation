package com.tdubuis.reservationapp.exception;

public class APINotAccessibleException extends RuntimeException {
    public APINotAccessibleException(String message) {
        super(message);
    }
}
