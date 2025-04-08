package com.jesus.prueba_tecnica.application.services;

import com.jesus.prueba_tecnica.domain.Price;

import java.time.LocalDateTime;

public interface PriceService {

    Price getApplicablePrice(LocalDateTime applicationDate, int productId, int brandId) throws Exception;
}
