package com.tdubuis.reservationapp.exception;

public class UidAlreadyExistOrNotConformException extends RuntimeException {
    public UidAlreadyExistOrNotConformException(String message) {
        super(message);
    }
}