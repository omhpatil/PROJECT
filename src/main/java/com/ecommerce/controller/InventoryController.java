package com.ecommerce.controller;

import com.ecommerce.dto.InventoryDTO;
import com.ecommerce.dto.StatusDTO;
import com.ecommerce.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO inventoryDTO) {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InventoryDTO> updateInventoryStatus(
            @PathVariable Long id,
            @RequestBody StatusDTO statusDTO) {   // StatusDTO has only `status` field
        return ResponseEntity.ok(inventoryService.updateStatus(id, statusDTO.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
