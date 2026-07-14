package com.example.inventoryservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock_levels")
public class StockLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private int quantityAvailable;
    private String warehouseLocation;

    public StockLevel() {}

    public StockLevel(Long productId, int quantityAvailable, String warehouseLocation) {
        this.productId = productId;
        this.quantityAvailable = quantityAvailable;
        this.warehouseLocation = warehouseLocation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public int getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) { this.quantityAvailable = quantityAvailable; }
    public String getWarehouseLocation() { return warehouseLocation; }
    public void setWarehouseLocation(String warehouseLocation) { this.warehouseLocation = warehouseLocation; }
}
