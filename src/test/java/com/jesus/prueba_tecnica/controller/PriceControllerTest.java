package com.jesus.prueba_tecnica.controller;

import com.jesus.prueba_tecnica.domain.Price;
import com.jesus.prueba_tecnica.application.services.PriceService;
import com.jesus.prueba_tecnica.utils.BBDDUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PriceControllerTest {

    @Autowired
    private PriceService priceService; // Acceso real al servicio

    @BeforeAll
    public static void setup() {
        BBDDUtils.inicializarBBDD();
    }

    @Test
    public void test1_precio_a_las_10_del_14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Price price = priceService.getApplicablePrice(applicationDate, 35455, 1);

        // Validar el precio esperado desde la BD
        assertTrue(price != null);
        assertEquals(35.50, price.getPrice());
    }

    @Test
    public void test2_precio_a_las_16_del_14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Price price = priceService.getApplicablePrice(applicationDate, 35455, 1);

        assertTrue(price != null);
        assertEquals(25.45, price.getPrice());
    }

    @Test
    public void test3_precio_a_las_21_del_14() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Price price = priceService.getApplicablePrice(applicationDate, 35455, 1);

        assertTrue(price != null);
        assertEquals(35.5, price.getPrice());
    }

    @Test
    public void test4_precio_a_las_10_del_15() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Price price = priceService.getApplicablePrice(applicationDate, 35455, 1);

        assertTrue(price != null);
        assertEquals(30.5, price.getPrice());
    }

    @Test
    public void test5_precio_a_las_21_del_16() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Price price = priceService.getApplicablePrice(applicationDate, 35455, 1);

        assertTrue(price != null);
        assertEquals(38.95, price.getPrice());
    }
}
