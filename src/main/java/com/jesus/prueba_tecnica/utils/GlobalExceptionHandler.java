package com.jesus.prueba_tecnica.utils;

import com.jesus.prueba_tecnica.application.exceptions.PriceNotFoundException;
import com.jesus.prueba_tecnica.infrastructure.exceptions.InvalidDateFormatException;
import com.jesus.prueba_tecnica.infrastructure.exceptions.RequestParamNoValidException;
import com.jesus.prueba_tecnica.utils.exceptions.DDBBException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePriceNotFoundException(PriceNotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Price not found", e.getMessage(), "PRICE_NOT_FOUND_ERROR");
    }

    @ExceptionHandler(RequestParamNoValidException.class)
    public ResponseEntity<Map<String, Object>> handleRequestParamNoValidException(RequestParamNoValidException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Request param invalid", e.getMessage(), "REQUEST_PARAM_NO_VALID_ERROR");
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidDateFormatException(InvalidDateFormatException e) {
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Date format invalid", e.getMessage(), "DATE_FORMAT_ERROR");
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<Map<String, Object>> handleDDBBException(DDBBException e) {
        return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, "DataBase error", e.getMessage(), "DATA_BASE_ERROR");
    }

    /**
     * Metodo encargado de mapear las excepciones
     * @param status
     * @param detail
     * @param message
     * @param title
     * @return
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String detail, String message, String title) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("code", status.value());
        errorDetails.put("detail", detail);
        errorDetails.put("message", message);
        errorDetails.put("title", title);
        errorResponse.put("error", errorDetails);

        return ResponseEntity.status(status).body(errorResponse);
    }

}
