package com.ecommerce.service;

import com.ecommerce.dto.PurchaseDTO;

import java.util.List;

public interface PurchaseService {

    List<PurchaseDTO> getAllPurchases();

    PurchaseDTO getPurchaseById(Long id);

    PurchaseDTO createPurchase(PurchaseDTO purchaseDTO);

    PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO);

    void deletePurchase(Long id);
}

