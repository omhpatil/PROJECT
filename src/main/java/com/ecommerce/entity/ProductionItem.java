package com.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "production_items")
public class ProductionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    private Integer quantity;

    public ProductionItem() {
    }

    public ProductionItem(Long id, Production production, Inventory inventory, Integer quantity) {
        this.id = id;
        this.production = production;
        this.inventory = inventory;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static ProductionItemBuilder builder() {
        return new ProductionItemBuilder();
    }

    public static class ProductionItemBuilder {
        private Production production;
        private Inventory inventory;
        private Integer quantity;

        public ProductionItemBuilder production(Production production) {
            this.production = production;
            return this;
        }

        public ProductionItemBuilder inventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public ProductionItemBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductionItem build() {
            return new ProductionItem(null, production, inventory, quantity);
        }
    }
}
