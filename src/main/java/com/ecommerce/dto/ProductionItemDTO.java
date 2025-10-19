package com.ecommerce.dto;

public class ProductionItemDTO {

    private Long inventoryId;
    private Integer quantity;

    public ProductionItemDTO() {
    }

    public ProductionItemDTO(Long inventoryId, Integer quantity) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static ProductionItemDTOBuilder builder() {
        return new ProductionItemDTOBuilder();
    }

    public static class ProductionItemDTOBuilder {
        private Long inventoryId;
        private Integer quantity;

        public ProductionItemDTOBuilder inventoryId(Long inventoryId) {
            this.inventoryId = inventoryId;
            return this;
        }

        public ProductionItemDTOBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductionItemDTO build() {
            return new ProductionItemDTO(inventoryId, quantity);
        }
    }
}
