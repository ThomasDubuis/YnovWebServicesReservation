package com.tdubuis.reservationapp.exception;


public class MovieNotExistException extends RuntimeException {
    public MovieNotExistException(String message) {
        super(message);
    }
}