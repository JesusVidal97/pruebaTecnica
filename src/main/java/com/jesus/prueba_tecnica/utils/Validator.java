package com.jesus.prueba_tecnica.utils;

import com.jesus.prueba_tecnica.infrastructure.exceptions.InvalidDateFormatException;
import com.jesus.prueba_tecnica.infrastructure.exceptions.RequestParamNoValidException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {

    /**
     * Metodo encargado de validar los datos de entrada
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId El id del producto por el que se va a filtrar
     * @param brandId El brandId por el que se va a filtrar
     */
    public static void dataValidator(String applicationDate, Integer productId, Integer brandId) {

        if(applicationDate == null){
            throw new RequestParamNoValidException("applicationDate cannot be null");
        }

        if(productId == null){
            throw new RequestParamNoValidException("productId cannot be null");
        } else if(productId < 0){
            throw new RequestParamNoValidException("productId cannot be negative");
        }

        if(brandId == null){
            throw new RequestParamNoValidException("brandId cannot be null");
        } else if(brandId < 0){
            throw new RequestParamNoValidException("brandId cannot be negative");
        }
    }

    public static LocalDateTime validateDateFormat(String applicationDate) {
        LocalDateTime parsedDate;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            parsedDate = LocalDateTime.parse(applicationDate, formatter);
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("El formato de fecha debe ser 'yyyy-MM-dd HH:mm:ss'.");
        }
    }
}
