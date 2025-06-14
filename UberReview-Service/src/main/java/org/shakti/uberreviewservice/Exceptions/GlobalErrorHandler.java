package org.shakti.uberreviewservice.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(CustomError.class)
    public ResponseEntity<ErrorMessage> globalErrorHandler(CustomError e, HttpServletRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), new Date() ,request.getRequestURI());
        return new ResponseEntity<>(errorMessage, e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception e) {
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
