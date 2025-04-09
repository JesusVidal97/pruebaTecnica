package com.jesus.prueba_tecnica.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase entidad de Price
 * @author Jesus 
 */
public class Price {
    private int brandId;
    private int productId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priority;
    private double price;
    private String currency;

    /**
     * Constructor del objeto
     * @param brandId
     * @param productId
     * @param priceList
     * @param startDate
     * @param endDate
     * @param priority
     * @param price
     * @param currency
     */
    public Price(int brandId, int productId, int priceList, LocalDateTime startDate, LocalDateTime endDate, int priority, double price, String currency) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

    /**
     * Constructor vacio del objeto
     */
    public Price() {
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return brandId == price1.brandId && productId == price1.productId && priceList == price1.priceList && priority == price1.priority && Double.compare(price, price1.price) == 0 && Objects.equals(startDate, price1.startDate) && Objects.equals(endDate, price1.endDate) && Objects.equals(currency, price1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, priceList, startDate, endDate, priority, price, currency);
    }

    @Override
    public String toString() {
        return "Price{" +
                "brandId=" + brandId +
                ", productId=" + productId +
                ", priceList=" + priceList +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priority=" + priority +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
