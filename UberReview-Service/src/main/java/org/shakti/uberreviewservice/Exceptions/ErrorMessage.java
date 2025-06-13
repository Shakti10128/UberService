package org.shakti.uberreviewservice.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage{
    private String errorMessage;
    private String uri;
    private boolean success = false;
    public ErrorMessage(String errorMessage, String uri) {
        this.errorMessage = errorMessage;
        this.uri = uri;
    }
}
