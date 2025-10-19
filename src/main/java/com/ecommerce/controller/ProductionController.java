package com.ecommerce.controller;

import com.ecommerce.dto.ProductionDTO;
import com.ecommerce.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productions")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public ResponseEntity<List<ProductionDTO>> getAllProductions() {
        return ResponseEntity.ok(productionService.getAllProductions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionDTO> getProductionById(@PathVariable Long id) {
        return ResponseEntity.ok(productionService.getProductionById(id));
    }

    @PostMapping
    public ResponseEntity<ProductionDTO> createProduction(@RequestBody ProductionDTO productionDTO) {
        return ResponseEntity.ok(productionService.createProduction(productionDTO));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ProductionDTO> updateProductionStatus(
            @PathVariable Long id,
            @RequestParam String status) {   // or @RequestBody if you pass JSON
        return ResponseEntity.ok(productionService.updateProductionStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduction(@PathVariable Long id) {
        productionService.deleteProduction(id);
        return ResponseEntity.noContent().build();
    }
}

