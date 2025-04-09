package com.jesus.prueba_tecnica.unitarios.controller;

import com.jesus.prueba_tecnica.application.services.PriceServiceImpl;
import com.jesus.prueba_tecnica.domain.Price;
import com.jesus.prueba_tecnica.application.exceptions.PriceNotFoundException;
import com.jesus.prueba_tecnica.infrastructure.controller.PriceController;
import com.jesus.prueba_tecnica.infrastructure.exceptions.InvalidDateFormatException;
import com.jesus.prueba_tecnica.utils.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceServiceImpl priceServiceImpl;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
    void testGetPrice_ReturnsValidPriceResponse() throws Exception {
        String applicationDate = "2025-04-08 23:50:00";
        int productId = 35455;
        int brandId = 1;
        LocalDateTime localDateTime = Validator.validateDateFormat(applicationDate);

        Price mockPrice = new Price(brandId, productId, 1, localDateTime, localDateTime.plusDays(1), 1, 35.50, "EUR");
        when(priceServiceImpl.getApplicablePrice(localDateTime, productId, brandId)).thenReturn(mockPrice);

        mockMvc.perform(get("/prices/find")
                        .param("applicationDate", applicationDate)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void testGetPrice_ThrowsPriceNotFoundException() {
        LocalDateTime localDateTime = LocalDateTime.of(2025, 4, 8, 23, 50);
        int productId = 35455;
        int brandId = 1;

        when(priceServiceImpl.getApplicablePrice(localDateTime, productId, brandId))
                .thenThrow(new PriceNotFoundException("No price found for product " + productId));

        assertThrows(PriceNotFoundException.class, () -> {
            priceController.getPrice("2025-04-08 23:50:00", productId, brandId);
        });
    }

    @Test
    void testGetPrice_ThrowsInvalidDateFormatException() {
        String invalidDate = "08-04-2025"; // Formato incorrecto

        assertThrows(InvalidDateFormatException.class, () -> {
            priceController.getPrice(invalidDate, 35455, 1);
        });
    }
}