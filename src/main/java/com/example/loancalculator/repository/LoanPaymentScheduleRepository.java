package com.example.loancalculator.repository;

import com.example.loancalculator.model.LoanPaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanPaymentScheduleRepository extends JpaRepository<LoanPaymentSchedule, Integer> {
    List<LoanPaymentSchedule> findByLoanDetailsLoanId(Integer loanId);
}
