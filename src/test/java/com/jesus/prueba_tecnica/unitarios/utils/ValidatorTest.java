package com.jesus.prueba_tecnica.unitarios.utils;

import com.jesus.prueba_tecnica.utils.Validator;
import com.jesus.prueba_tecnica.infrastructure.exceptions.RequestParamNoValidException;
import com.jesus.prueba_tecnica.infrastructure.exceptions.InvalidDateFormatException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testDataValidator_ThrowsException_WhenApplicationDateIsNull() {
        Exception exception = assertThrows(RequestParamNoValidException.class, () -> {
            Validator.dataValidator(null, 35455, 1);
        });

        assertEquals("applicationDate cannot be null", exception.getMessage());
    }

    @Test
    void testDataValidator_ThrowsException_WhenProductIdIsNull() {
        Exception exception = assertThrows(RequestParamNoValidException.class, () -> {
            Validator.dataValidator("2025-04-08 23:50:00", null, 1);
        });

        assertEquals("productId cannot be null", exception.getMessage());
    }

    @Test
    void testDataValidator_ThrowsException_WhenProductIdIsNegative() {
        Exception exception = assertThrows(RequestParamNoValidException.class, () -> {
            Validator.dataValidator("2025-04-08 23:50:00", -1, 1);
        });

        assertEquals("productId cannot be negative", exception.getMessage());
    }

    @Test
    void testDataValidator_ThrowsException_WhenBrandIdIsNull() {
        Exception exception = assertThrows(RequestParamNoValidException.class, () -> {
            Validator.dataValidator("2025-04-08 23:50:00", 35455, null);
        });

        assertEquals("brandId cannot be null", exception.getMessage());
    }

    @Test
    void testDataValidator_ThrowsException_WhenBrandIdIsNegative() {
        Exception exception = assertThrows(RequestParamNoValidException.class, () -> {
            Validator.dataValidator("2025-04-08 23:50:00", 35455, -1);
        });

        assertEquals("brandId cannot be negative", exception.getMessage());
    }

    @Test
    void testDataValidator_DoesNotThrowException_WhenAllFieldsAreValid() {
        assertDoesNotThrow(() -> {
            Validator.dataValidator("2025-04-08 23:50:00", 35455, 1);
        });
    }

    @Test
    void testValidateDateFormat_ReturnsLocalDateTime_WhenFormatIsValid() {
        LocalDateTime result = Validator.validateDateFormat("2025-04-08 23:50:00");

        assertNotNull(result);
        assertEquals(LocalDateTime.of(2025, 4, 8, 23, 50), result);
    }

    @Test
    void testValidateDateFormat_ThrowsException_WhenFormatIsInvalid() {
        Exception exception = assertThrows(InvalidDateFormatException.class, () -> {
            Validator.validateDateFormat("08-04-2025");
        });

        assertEquals("El formato de fecha debe ser 'yyyy-MM-dd HH:mm:ss'.", exception.getMessage());
    }
}
