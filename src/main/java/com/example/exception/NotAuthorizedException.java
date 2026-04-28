package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción equivalente a NotAuthorizedException de JAX-RS.
 *
 * La anotación @ResponseStatus hace que Spring automáticamente
 * devuelva un HTTP 401 Unauthorized cuando esta excepción se lanza.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
