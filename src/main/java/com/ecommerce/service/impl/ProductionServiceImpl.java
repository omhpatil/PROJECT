package com.ecommerce.service.impl;

import com.ecommerce.dto.ProductionDTO;
import com.ecommerce.dto.ProductionItemDTO;
import com.ecommerce.entity.Inventory;
import com.ecommerce.entity.Production;
import com.ecommerce.entity.ProductionItem;
import com.ecommerce.repository.InventoryRepository;
import com.ecommerce.repository.ProductionRepository;
import com.ecommerce.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;
    private final InventoryRepository inventoryRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository, InventoryRepository inventoryRepository) {
        this.productionRepository = productionRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<ProductionDTO> getAllProductions() {
        return productionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductionDTO getProductionById(Long id) {
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Production not found"));
        return convertToDTO(production);
    }

    @Override
    public ProductionDTO createProduction(ProductionDTO dto) {
        Production production = new Production();
        production.setName(dto.getName());
        production.setDate(dto.getDate());

        List<ProductionItem> items = dto.getProductionItems().stream()
                .map(itemDTO -> {
                    Inventory inventory = inventoryRepository.findById(itemDTO.getInventoryId())
                            .orElseThrow(() -> new RuntimeException("Inventory not found"));

                    if (inventory.getQuantity() < itemDTO.getQuantity()) {
                        throw new RuntimeException("Not enough stock in inventory " + inventory.getId());
                    }

                    inventory.setQuantity(inventory.getQuantity() - itemDTO.getQuantity());
                    inventoryRepository.save(inventory);

                    return ProductionItem.builder()
                            .production(production)
                            .inventory(inventory)
                            .quantity(itemDTO.getQuantity())
                            .build();
                }).collect(Collectors.toList());

        production.setProductionItems(items);
        production.setStatus("PENDING"); // default status
        Production saved = productionRepository.save(production);

        return convertToDTO(saved);
    }

    @Override
    public ProductionDTO updateProductionStatus(Long id, String status) {
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Production not found"));
        production.setStatus(status);
        Production saved = productionRepository.save(production);
        return convertToDTO(saved);
    }

    @Override
    public void deleteProduction(Long id) {
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Production not found"));
        productionRepository.delete(production);
    }

    private ProductionDTO convertToDTO(Production production) {
        List<ProductionItemDTO> itemDTOs = production.getProductionItems()
                .stream()
                .map(item -> ProductionItemDTO.builder()
                        .inventoryId(item.getInventory().getId())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return ProductionDTO.builder()
                .id(production.getId())
                .name(production.getName())
                .date(production.getDate())
                .status(production.getStatus())
                .productionItems(itemDTOs)
                .build();
    }
}
