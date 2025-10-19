package com.ecommerce.service;

import com.ecommerce.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventories();
    InventoryDTO getInventoryById(Long id);
    InventoryDTO createInventory(InventoryDTO inventoryDTO);
    void deleteInventory(Long id);
    InventoryDTO updateStatus(Long id, String status);
}
