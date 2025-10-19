package com.ecommerce.dto;

import java.time.LocalDate;

public class PurchaseDTO {

    private Long id;
    private String name;
    private Double price;
    private LocalDate expectedDate;

    public PurchaseDTO() {
    }

    public PurchaseDTO(Long id, String name, Double price, LocalDate expectedDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expectedDate = expectedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(LocalDate expectedDate) {
        this.expectedDate = expectedDate;
    }

    public static PurchaseDTOBuilder builder() {
        return new PurchaseDTOBuilder();
    }

    public static class PurchaseDTOBuilder {
        private Long id;
        private String name;
        private Double price;
        private LocalDate expectedDate;

        public PurchaseDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PurchaseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PurchaseDTOBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public PurchaseDTOBuilder expectedDate(LocalDate expectedDate) {
            this.expectedDate = expectedDate;
            return this;
        }

        public PurchaseDTO build() {
            return new PurchaseDTO(id, name, price, expectedDate);
        }
    }
}
