package com.example.loancalculator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LoanDetailsDTO {

    @NotNull(message = "Loan amount must not be null")
    private BigDecimal amount;
    @NotNull(message = "Interest rate must not be null")
    @Min(value = 0, message = "Interest rate must be greater than or equal to 0")
    private BigDecimal annualInterestRate;
    @NotNull(message = "Number of periods must not be null")
    @Min(value = 1, message = "Number of periods must be greater than 0")
    private Integer months;

    public LoanDetailsDTO() {
    }


    public LoanDetailsDTO(BigDecimal amount, BigDecimal annualInterestRate, Integer months) {
        this.amount = amount;
        this.annualInterestRate = annualInterestRate;
        this.months = months;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(BigDecimal annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    @Override
    public String toString() {
        return "LoanCalculationRequest{" +
                "amount=" + amount +
                ", annualInterestRate=" + annualInterestRate +
                ", months=" + months +
                '}';
    }
}
