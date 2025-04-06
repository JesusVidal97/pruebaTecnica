package com.jesus.prueba_tecnica.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase de respuesta de la operacion obtener precio
 * @author Jesus
 */
public class PriceResponseDTO {
    private int productId;
    private int brandId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;

    /**
     * Constructor de la clase
     * @param productId
     * @param brandId
     * @param priceList
     * @param startDate
     * @param endDate
     * @param price
     */
    public PriceResponseDTO(int productId, int brandId, int priceList, LocalDateTime startDate, LocalDateTime endDate, double price) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    /**
     * Constructor vacio de la clase
     */
    public PriceResponseDTO() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getPriceList() {
        return priceList;
    }

    public void setPriceList(int priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PriceResponseDTO that = (PriceResponseDTO) o;
        return productId == that.productId && brandId == that.brandId && priceList == that.priceList && Double.compare(price, that.price) == 0 && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, brandId, priceList, startDate, endDate, price);
    }

    @Override
    public String toString() {
        return "PriceResponseDTO{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", priceList=" + priceList +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}