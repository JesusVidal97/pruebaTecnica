package com.jesus.prueba_tecnica.repository.dao;

import com.jesus.prueba_tecnica.repository.entity.Price;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PriceRepositoryH2Impl implements PriceRepository {
    private final JdbcTemplate jdbcTemplate;

    public PriceRepositoryH2Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Metodo que lanza la query para obtener el precio del producto con los datos filtrados
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId El id del producto por el que se va a filtrar
     * @param brandId El brandId por el que se va a filtrar
     * @return {@link Optional} que contiene el {@link Price} si se encuentra, o un Optional vacío si no hay precio aplicable.
     */
    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, int productId, int brandId) {
        String sql = "SELECT * FROM PRICES WHERE ProductId = ? AND BrandId = ? AND StartDate <= ? AND EndDate >= ? ORDER BY Priority DESC LIMIT 1";
        return jdbcTemplate.query(sql,
                new Object[]{productId, brandId, applicationDate, applicationDate},
                (rs, rowNum) -> new Price(
                        rs.getInt("BrandId"),
                        rs.getInt("ProductId"),
                        rs.getInt("PriceList"),
                        rs.getTimestamp("StartDate").toLocalDateTime(),
                        rs.getTimestamp("EndDate").toLocalDateTime(),
                        rs.getDouble("Price"),
                        rs.getString("Currency")
                )).stream().findFirst();
    }
}
