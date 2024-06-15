package com.tdubuis.reservationapp.exception;

public class NoSeatLeftException extends RuntimeException {
    public NoSeatLeftException(String message) {
        super(message);
    }
}
