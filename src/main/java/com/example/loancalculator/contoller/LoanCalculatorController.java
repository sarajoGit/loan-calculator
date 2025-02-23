package com.example.loancalculator.contoller;

import com.example.loancalculator.dto.LoanCalculationDTO;
import com.example.loancalculator.dto.LoanDetailsDTO;
import com.example.loancalculator.service.LoanCalculatorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoanCalculatorController {
    private static final Logger log = LoggerFactory.getLogger(LoanCalculatorController.class);

    private final LoanCalculatorService loanCalculatorService;

    @Autowired
    public LoanCalculatorController(LoanCalculatorService loanCalculatorService) {
        this.loanCalculatorService = loanCalculatorService;
    }

    @PostMapping("/loans")
    public ResponseEntity<LoanCalculationDTO> calculateLoan(@RequestBody @Valid LoanDetailsDTO request) {
        try {
            LoanCalculationDTO response = loanCalculatorService.calculateLoan(request.getAmount(), request.getAnnualInterestRate(), request.getMonths());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Unexpected error occurred while calculating loan: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
