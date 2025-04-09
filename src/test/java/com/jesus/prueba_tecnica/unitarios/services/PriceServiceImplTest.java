package com.jesus.prueba_tecnica.unitarios.services;

import com.jesus.prueba_tecnica.application.exceptions.PriceNotFoundException;
import com.jesus.prueba_tecnica.application.services.PriceServiceImpl;
import com.jesus.prueba_tecnica.domain.Price;
import com.jesus.prueba_tecnica.infrastructure.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApplicablePrice_ReturnsHighestPriorityPrice() {
        LocalDateTime date = LocalDateTime.of(2025, 4, 8, 23, 44);
        int productId = 35455;
        int brandId = 1;

        List<Price> priceList = Arrays.asList(
                new Price(brandId, productId, 1, date, date.plusDays(1), 1, 35.50, "EUR"),
                new Price(brandId, productId, 2, date, date.plusDays(1), 2,  25.45, "EUR"),
                new Price(brandId, productId, 3, date, date.plusDays(1), 3,  40.00, "EUR") // Mayor prioridad
        );

        when(priceRepository.findApplicablePrice(date, productId, brandId)).thenReturn(priceList);

        Price result = priceService.getApplicablePrice(date, productId, brandId);

        assertNotNull(result);
        assertEquals(3, result.getPriceList()); // Debe ser el de mayor prioridad
        assertEquals(40.00, result.getPrice());
    }

    @Test
    void testGetApplicablePrice_ThrowsException_WhenPriceListIsEmpty() {
        LocalDateTime date = LocalDateTime.of(2025, 4, 8, 23, 44);
        int productId = 35455;
        int brandId = 1;

        when(priceRepository.findApplicablePrice(date, productId, brandId)).thenReturn(List.of());

        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            priceService.getApplicablePrice(date, productId, brandId);
        });

        assertEquals("No price found for product 35455 and brand 1", exception.getMessage());
    }

    @Test
    void testGetApplicablePrice_ThrowsException_WhenPriceListIsNull() {
        LocalDateTime date = LocalDateTime.of(2025, 4, 8, 23, 44);
        int productId = 35455;
        int brandId = 1;

        when(priceRepository.findApplicablePrice(date, productId, brandId)).thenReturn(null);

        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            priceService.getApplicablePrice(date, productId, brandId);
        });

        assertEquals("No price found for product 35455 and brand 1", exception.getMessage());
    }
}
