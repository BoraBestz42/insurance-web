package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "purchase_history", schema = "insurance")
public class PurchaseHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_ids", nullable = false)
    private String productIds;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "purchase_timestamp", nullable = false)
    private LocalDateTime purchaseTimestamp;

    public PurchaseHistoryModel() {
    }

    public PurchaseHistoryModel(String productIds, Integer totalPrice) {
        this.productIds = productIds;
        this.totalPrice = totalPrice;
        this.purchaseTimestamp = LocalDateTime.now();
    }
}
