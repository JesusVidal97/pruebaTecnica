package com.jesus.prueba_tecnica.infrastructure.repository.dao;

import com.jesus.prueba_tecnica.domain.Price;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findApplicablePrice(LocalDateTime applicationDate, int productId, int brandId);
}
