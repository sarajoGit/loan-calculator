package com.example.loancalculator.repository;

import com.example.loancalculator.model.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {
}
