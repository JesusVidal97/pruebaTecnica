package com.jesus.prueba_tecnica.infrastructure.controller;

import com.jesus.prueba_tecnica.infrastructure.dto.PriceResponseDTO;
import com.jesus.prueba_tecnica.domain.Price;
import com.jesus.prueba_tecnica.application.services.PriceServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controlador del proyecto que contiene los endpoints.
 * @author Jesus
 */
@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceServiceImpl priceServiceImpl;

    public PriceController(PriceServiceImpl priceServiceImpl) {
        this.priceServiceImpl = priceServiceImpl;
    }

    /**
     * Endpoint que recibe una serie de parametros para filtrar y devolver el precio del producto
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId El id del producto por el que se va a filtrar
     * @param brandId El brandId por el que se va a filtrar
     * @return {@link PriceResponseDTO}
     */
    @GetMapping("/find")
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
            @RequestParam int productId,
            @RequestParam int brandId) throws Exception {

        Price price = priceServiceImpl.getApplicablePrice(applicationDate, productId, brandId);

        PriceResponseDTO priceResponseDTO = new PriceResponseDTO(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );

        return ResponseEntity.ok(priceResponseDTO);
    }
}
