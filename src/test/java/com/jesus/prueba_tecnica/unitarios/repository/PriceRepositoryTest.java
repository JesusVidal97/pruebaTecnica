package com.jesus.prueba_tecnica.unitarios.repository;

import com.jesus.prueba_tecnica.domain.Price;
import com.jesus.prueba_tecnica.infrastructure.repository.PriceRepository;
import com.jesus.prueba_tecnica.infrastructure.repository.PriceRepositoryH2Impl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PriceRepositoryH2Impl priceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindApplicablePrice_ReturnsPriceList() {
        LocalDateTime applicationDate = LocalDateTime.of(2025, 4, 8, 23, 50);
        int productId = 35455;
        int brandId = 1;

        List<Price> mockPrices = Arrays.asList(
                new Price(brandId, productId, 1, applicationDate, applicationDate.plusDays(1), 1, 35.50, "EUR"),
                new Price(brandId, productId, 2, applicationDate, applicationDate.plusDays(1), 2, 25.45, "EUR")
        );

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(mockPrices);

        List<Price> result = priceRepository.findApplicablePrice(applicationDate, productId, brandId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(35455, result.get(0).getProductId());
    }

    @Test
    void testFindApplicablePrice_ReturnsEmptyList_WhenNoPricesFound() {
        LocalDateTime applicationDate = LocalDateTime.of(2025, 4, 8, 23, 50);
        int productId = 99999; // Producto inexistente
        int brandId = 1;

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(List.of());

        List<Price> result = priceRepository.findApplicablePrice(applicationDate, productId, brandId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testRowMapper_CorrectlyMapsResultSet() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("BrandId")).thenReturn(1);
        when(resultSet.getInt("ProductId")).thenReturn(35455);
        when(resultSet.getInt("PriceList")).thenReturn(1);
        when(resultSet.getTimestamp("StartDate")).thenReturn(Timestamp.valueOf(LocalDateTime.of(2025, 4, 8, 23, 50)));
        when(resultSet.getTimestamp("EndDate")).thenReturn(Timestamp.valueOf(LocalDateTime.of(2025, 4, 9, 23, 50)));
        when(resultSet.getInt("priority")).thenReturn(2);
        when(resultSet.getDouble("Price")).thenReturn(35.50);
        when(resultSet.getString("Currency")).thenReturn("EUR");

        RowMapper<Price> rowMapper = (rs, rowNum) -> new Price(
                rs.getInt("BrandId"),
                rs.getInt("ProductId"),
                rs.getInt("PriceList"),
                rs.getTimestamp("StartDate").toLocalDateTime(),
                rs.getTimestamp("EndDate").toLocalDateTime(),
                rs.getInt("priority"),
                rs.getDouble("Price"),
                rs.getString("Currency")
        );

        Price result = rowMapper.mapRow(resultSet, 1);

        assertEquals(1, result.getBrandId());
        assertEquals(35455, result.getProductId());
        assertEquals(35.50, result.getPrice());
        assertEquals("EUR", result.getCurrency());
    }
}
