package com.sys.reservation.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReservationExistException extends RuntimeException{
    public ReservationExistException(String s) {
        super(s);
    }
}
