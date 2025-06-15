package com.example.demo.Repository;

import com.example.demo.Model.InsuranceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<InsuranceModel, Long> {

}
