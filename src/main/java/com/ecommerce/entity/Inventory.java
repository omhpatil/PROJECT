package com.ecommerce.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each inventory item comes from a purchase
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private Integer quantity;
    private String status;
    private LocalDate arriveDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Inventory() {
    }

    public Inventory(Long id, Purchase purchase, Integer quantity, String status,
                     LocalDate arriveDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.purchase = purchase;
        this.quantity = quantity;
        this.status = status;
        this.arriveDate = arriveDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
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
        this.updatedAt = LocalDateTime.now(); // set both initially
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static InventoryBuilder builder() {
        return new InventoryBuilder();
    }

    public static class InventoryBuilder {
        private Purchase purchase;
        private Integer quantity;
        private String status;
        private LocalDate arriveDate;

        public InventoryBuilder purchase(Purchase purchase) {
            this.purchase = purchase;
            return this;
        }

        public InventoryBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public InventoryBuilder status(String status) {
            this.status = status;
            return this;
        }

        public InventoryBuilder arriveDate(LocalDate arriveDate) {
            this.arriveDate = arriveDate;
            return this;
        }

        public Inventory build() {
            return new Inventory(null, purchase, quantity, status, arriveDate, null, null);
        }
    }
}
