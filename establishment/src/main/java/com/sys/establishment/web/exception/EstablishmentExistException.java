package com.sys.establishment.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EstablishmentExistException extends RuntimeException{
    public EstablishmentExistException (String s) {
        super(s);
    }
}
