package com.jesus.prueba_tecnica.infrastructure.exceptions;

public class RequestParamNoValidException extends RuntimeException {

    public RequestParamNoValidException(String message) {
        super(message);
    }
}
