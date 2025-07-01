package com.example.demo.Repository;

import com.example.demo.Model.PurchaseHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistoryModel, Long> {

}
