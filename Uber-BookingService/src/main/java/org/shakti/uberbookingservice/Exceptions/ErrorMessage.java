package org.shakti.uberbookingservice.Exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage{
    private String errorMessage;
    private String uri;
    private Date timestamp;
    private boolean success = false;
    public ErrorMessage(String errorMessage, Date timestamp ,String uri) {
        this.errorMessage = errorMessage;
        this.uri = uri;
        this.timestamp = timestamp;
    }
}
