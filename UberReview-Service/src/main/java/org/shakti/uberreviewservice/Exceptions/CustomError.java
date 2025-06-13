package org.shakti.uberreviewservice.Exceptions;

import lombok.Getter;
import lombok.Setter;
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
