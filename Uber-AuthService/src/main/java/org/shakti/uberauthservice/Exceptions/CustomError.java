package org.shakti.uberauthservice.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomError extends RuntimeException{
    private String message;
    private HttpStatus statusCode;
    public CustomError(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
