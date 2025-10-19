package com.ecommerce.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productions")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate date;

    private String status;

    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductionItem> productionItems = new ArrayList<>();

    public Production() {
    }

    public Production(Long id, String name, LocalDate date, String status, List<ProductionItem> productionItems) {
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

    public List<ProductionItem> getProductionItems() {
        return productionItems;
    }

    public void setProductionItems(List<ProductionItem> productionItems) {
        this.productionItems = productionItems;
    }

    public static ProductionBuilder builder() {
        return new ProductionBuilder();
    }

    public static class ProductionBuilder {
        private Long id;
        private String name;
        private LocalDate date;
        private String status;
        private List<ProductionItem> productionItems = new ArrayList<>();

        public ProductionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductionBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public ProductionBuilder status(String status) {
            this.status = status;
            return this;
        }

        public ProductionBuilder productionItems(List<ProductionItem> productionItems) {
            this.productionItems = productionItems;
            return this;
        }

        public Production build() {
            return new Production(id, name, date, status, productionItems);
        }
    }
}
