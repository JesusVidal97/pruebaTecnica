package com.jesus.prueba_tecnica.infrastructure.repository.dao;

import com.jesus.prueba_tecnica.domain.Price;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa los metodos CRUD de la BBDD H2
 * @author Jesus
 */
@Repository
public class PriceRepositoryH2Impl implements PriceRepository {
    private final JdbcTemplate jdbcTemplate;

    public PriceRepositoryH2Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Metodo que lanza la query para obtener el precio del producto con los datos filtrados
     *
     * @param applicationDate La fecha en la que se va a filtrar
     * @param productId       El id del producto por el que se va a filtrar
     * @param brandId         El brandId por el que se va a filtrar
     * @return {@link Optional} que contiene el {@link Price} si se encuentra, o un Optional vacío si no hay precio aplicable.
     */
    @Override
    public List<Price> findApplicablePrice(LocalDateTime applicationDate, int productId, int brandId) {
        String sql = "SELECT * FROM PRICES WHERE ProductId = ? AND BrandId = ? AND StartDate <= ? AND EndDate >= ?";
        return jdbcTemplate.query(sql,
                new Object[]{productId, brandId, applicationDate, applicationDate},
                (rs, rowNum) -> new Price(
                        rs.getInt("BrandId"),
                        rs.getInt("ProductId"),
                        rs.getInt("PriceList"),
                        rs.getTimestamp("StartDate").toLocalDateTime(),
                        rs.getTimestamp("EndDate").toLocalDateTime(),
                        rs.getInt("priority"),
                        rs.getDouble("Price"),
                        rs.getString("Currency")
                ));
    }
}
