package com.example.demo.Service;

import com.example.demo.Model.InsuranceModel;
import com.example.demo.Model.PurchaseHistoryModel;
import com.example.demo.Repository.InsuranceRepository;
import com.example.demo.Repository.PurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    public List<InsuranceModel> getAllProducts() {
        return insuranceRepository.findAll();
    }
    public int sumCartItems(List<InsuranceModel> cart) {
        return cart.stream()
                .mapToInt(InsuranceModel::getPrice)
                .sum();
    }

    public void removeItemFromCart(Long productId, List<InsuranceModel> cart) {
        cart.removeIf(product -> product.getId().equals(productId));
    }

    public void savePurchaseHistory(List<Long> productIds, Integer totalPrice) {
        String productIdInCart = productIds.toString();
        PurchaseHistoryModel history = new PurchaseHistoryModel(productIdInCart, totalPrice);
        purchaseHistoryRepository.save(history);
    }
}
