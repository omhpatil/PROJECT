package com.ecommerce.service.impl;

import com.ecommerce.dto.PurchaseDTO;
import com.ecommerce.entity.Inventory;
import com.ecommerce.entity.Purchase;
import com.ecommerce.repository.InventoryRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.service.PurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final InventoryRepository inventoryRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, InventoryRepository inventoryRepository) {
        this.purchaseRepository = purchaseRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO getPurchaseById(Long id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
        return convertToDTO(purchase);
    }

    @Override
    public PurchaseDTO createPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = Purchase.builder()
                .name(purchaseDTO.getName())
                .price(purchaseDTO.getPrice())
                .expectedDate(purchaseDTO.getExpectedDate())
                .build();

        Purchase saved = purchaseRepository.save(purchase);
        return convertToDTO(saved);
    }

    @Override
    public PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO) {
        Purchase existing = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        existing.setName(purchaseDTO.getName());
        existing.setPrice(purchaseDTO.getPrice());
        existing.setExpectedDate(purchaseDTO.getExpectedDate());

        Purchase updated = purchaseRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void deletePurchase(Long id) {
        // 🔹 Manually remove inventories first (safe even with cascade)
        List<Inventory> inventories = inventoryRepository.findByPurchaseId(id);
        inventoryRepository.deleteAll(inventories);

        // 🔹 Then delete purchase
        purchaseRepository.deleteById(id);
    }

    private PurchaseDTO convertToDTO(Purchase purchase) {
        return PurchaseDTO.builder()
                .id(purchase.getId())
                .name(purchase.getName())
                .price(purchase.getPrice())
                .expectedDate(purchase.getExpectedDate())
                .build();
    }
}
