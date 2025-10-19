package com.ecommerce.controller;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        return ResponseEntity.ok(purchaseService.getAllPurchases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }

    @PostMapping
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return ResponseEntity.ok(purchaseService.createPurchase(purchaseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDTO> updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDTO) {
        return ResponseEntity.ok(purchaseService.updatePurchase(id, purchaseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }
}

