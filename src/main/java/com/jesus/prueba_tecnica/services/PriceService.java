package com.jesus.prueba_tecnica.services;

import com.jesus.prueba_tecnica.repository.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<Price> getApplicablePrice(LocalDateTime applicationDate, int productId, int brandId);
}
