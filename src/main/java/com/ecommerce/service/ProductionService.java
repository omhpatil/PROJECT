package com.ecommerce.service;

import com.ecommerce.dto.ProductionDTO;

import java.util.List;

public interface ProductionService {
    List<ProductionDTO> getAllProductions();
    ProductionDTO createProduction(ProductionDTO productionDTO);
    ProductionDTO getProductionById(Long id);

    ProductionDTO updateProductionStatus(Long id, String status);
    void deleteProduction(Long id);
}
