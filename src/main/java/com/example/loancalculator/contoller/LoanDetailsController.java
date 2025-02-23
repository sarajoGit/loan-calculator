package com.example.loancalculator.contoller;

import com.example.loancalculator.dto.LoanDetailsDTO;
import com.example.loancalculator.dto.PaymentSchedule;
import com.example.loancalculator.service.LoanDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LoanDetailsController {

    private final LoanDetailsService loanService;

    @Autowired
    public LoanDetailsController(LoanDetailsService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/loans/{loanId}")
    public ResponseEntity<LoanDetailsDTO> getLoanDetails(@PathVariable Integer loanId) {
        LoanDetailsDTO response = loanService.getLoanDetails(loanId);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/loans/{loanId}/payments")
    public ResponseEntity<List<PaymentSchedule>> getLoanPaymentSchedule(@PathVariable Integer loanId) {
        List<PaymentSchedule> response = loanService.getLoanPaymentSchedule(loanId);
        if (response != null && !response.isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
