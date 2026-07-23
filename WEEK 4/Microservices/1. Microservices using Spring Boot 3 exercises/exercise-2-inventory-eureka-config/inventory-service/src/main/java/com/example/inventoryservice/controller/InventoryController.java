package com.example.inventoryservice.controller;

import com.example.inventoryservice.client.ProductClient;
import com.example.inventoryservice.entity.StockLevel;
import com.example.inventoryservice.repository.StockLevelRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final StockLevelRepository stockLevelRepository;
    private final ProductClient productClient;

    public InventoryController(StockLevelRepository stockLevelRepository, ProductClient productClient) {
        this.stockLevelRepository = stockLevelRepository;
        this.productClient = productClient;
    }

    @PostMapping
    public ResponseEntity<StockLevel> createStockLevel(@RequestBody StockLevel stockLevel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockLevelRepository.save(stockLevel));
    }

    @GetMapping
    public List<StockLevel> getAllStockLevels() {
        return stockLevelRepository.findAll();
    }

    // Combines local inventory data with a live call to product-service (via Eureka + Feign)
    @GetMapping("/{productId}/details")
    public ResponseEntity<?> getStockWithProductDetails(@PathVariable Long productId) {
        return stockLevelRepository.findByProductId(productId).map(stock -> {
            try {
                Map<String, Object> product = productClient.getProductById(productId);
                return ResponseEntity.ok(Map.of("stock", stock, "product", product));
            } catch (FeignException.NotFound e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Product " + productId + " not found in product-service");
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}
