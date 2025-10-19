package com.ecommerce.service.impl;

import com.ecommerce.dto.InventoryDTO;
import com.ecommerce.entity.Inventory;
import com.ecommerce.entity.Purchase;
import com.ecommerce.repository.InventoryRepository;
import com.ecommerce.repository.PurchaseRepository;
import com.ecommerce.service.InventoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final PurchaseRepository purchaseRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, PurchaseRepository purchaseRepository) {
        this.inventoryRepository = inventoryRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public List<InventoryDTO> getAllInventories() {
        return inventoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        return convertToDTO(inventory);
    }

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Purchase purchase = purchaseRepository.findById(inventoryDTO.getPurchaseId())
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        Inventory inventory = Inventory.builder()
                .purchase(purchase)
                .quantity(inventoryDTO.getQuantity())
                .status("Pending")
                .arriveDate(inventoryDTO.getArriveDate())
                .build();

        Inventory saved = inventoryRepository.save(inventory);
        return convertToDTO(saved);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public InventoryDTO updateStatus(Long id, String status) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setStatus(status);

        if ("Approved".equalsIgnoreCase(status)) {
            inventory.setArriveDate(LocalDate.now());
        }

        Inventory updated = inventoryRepository.save(inventory);
        return convertToDTO(updated);
    }

    private InventoryDTO convertToDTO(Inventory inventory) {
        return InventoryDTO.builder()
                .id(inventory.getId())
                .purchaseId(inventory.getPurchase().getId())
                .quantity(inventory.getQuantity())
                .status(inventory.getStatus())
                .arriveDate(inventory.getArriveDate())
                .build();
    }
}
