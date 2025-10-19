package com.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

public class ProductionDTO {

    private Long id;
    private String name;
    private LocalDate date;
    private String status;
    private List<ProductionItemDTO> productionItems;

    public ProductionDTO() {
    }

    public ProductionDTO(Long id, String name, LocalDate date, String status, List<ProductionItemDTO> productionItems) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.status = status;
        this.productionItems = productionItems;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductionItemDTO> getProductionItems() {
        return productionItems;
    }

    public void setProductionItems(List<ProductionItemDTO> productionItems) {
        this.productionItems = productionItems;
    }

    public static ProductionDTOBuilder builder() {
        return new ProductionDTOBuilder();
    }

    public static class ProductionDTOBuilder {
        private Long id;
        private String name;
        private LocalDate date;
        private String status;
        private List<ProductionItemDTO> productionItems;

        public ProductionDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductionDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductionDTOBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public ProductionDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ProductionDTOBuilder productionItems(List<ProductionItemDTO> productionItems) {
            this.productionItems = productionItems;
            return this;
        }

        public ProductionDTO build() {
            return new ProductionDTO(id, name, date, status, productionItems);
        }
    }
}
