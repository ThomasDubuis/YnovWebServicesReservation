package com.tdubuis.reservationapp.exception;

public class ExpiredReservationException extends RuntimeException {
    public ExpiredReservationException(String message) {
        super(message);
    }
}
