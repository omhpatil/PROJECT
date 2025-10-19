package com.ecommerce.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private LocalDate expectedDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Purchase() {
    }

    public Purchase(Long id, String name, Double price, LocalDate expectedDate,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expectedDate = expectedDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static PurchaseBuilder builder() {
        return new PurchaseBuilder();
    }

    public static class PurchaseBuilder {
        private String name;
        private Double price;
        private LocalDate expectedDate;

        public PurchaseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PurchaseBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public PurchaseBuilder expectedDate(LocalDate expectedDate) {
            this.expectedDate = expectedDate;
            return this;
        }

        public Purchase build() {
            return new Purchase(null, name, price, expectedDate, null, null);
        }
    }
}
