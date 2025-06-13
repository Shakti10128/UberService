package org.shakti.uberreviewservice.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Error.class)
    public ResponseEntity<ErrorMessage> globalErrorHandler(CustomError e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorMessage, e.getStatusCode());
    }
}
