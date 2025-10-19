package com.ecommerce.dto;

import java.time.LocalDate;

public class InventoryDTO {

    private Long id;
    private Long purchaseId;
    private Integer quantity;
    private String status;
    private LocalDate arriveDate;

    public InventoryDTO() {}

    public InventoryDTO(Long id, Long purchaseId, Integer quantity, String status, LocalDate arriveDate) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.quantity = quantity;
        this.status = status;
        this.arriveDate = arriveDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
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

    public static InventoryDTOBuilder builder() {
        return new InventoryDTOBuilder();
    }

    public static class InventoryDTOBuilder {
        private Long id;
        private Long purchaseId;
        private Integer quantity;
        private String status;
        private LocalDate arriveDate;

        public InventoryDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public InventoryDTOBuilder purchaseId(Long purchaseId) {
            this.purchaseId = purchaseId;
            return this;
        }

        public InventoryDTOBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public InventoryDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public InventoryDTOBuilder arriveDate(LocalDate arriveDate) {
            this.arriveDate = arriveDate;
            return this;
        }

        public InventoryDTO build() {
            return new InventoryDTO(id, purchaseId, quantity, status, arriveDate);
        }
    }
}
