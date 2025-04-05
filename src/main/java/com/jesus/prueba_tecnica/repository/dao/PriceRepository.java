package com.jesus.prueba_tecnica.repository.dao;

import com.jesus.prueba_tecnica.repository.entity.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, int productId, int brandId);
}
