package com.example.loancalculator.dto;

import java.math.BigDecimal;
import java.util.List;

public class LoanCalculationDTO {

    private Integer loanId;
    private BigDecimal amount;
    private BigDecimal annualInterestRate;
    private int numMonths;
    private List<PaymentSchedule> paymentSchedule;

    public LoanCalculationDTO() {
    }

    public LoanCalculationDTO(Integer loanId, BigDecimal amount, BigDecimal annualInterestRate, int numMonths, List<PaymentSchedule> paymentSchedule) {
        this.loanId = loanId;
        this.amount = amount;
        this.annualInterestRate = annualInterestRate;
        this.numMonths = numMonths;
        this.paymentSchedule = paymentSchedule;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
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

    public int getNumMonths() {
        return numMonths;
    }

    public void setNumMonths(int numMonths) {
        this.numMonths = numMonths;
    }

    public List<PaymentSchedule> getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(List<PaymentSchedule> paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    @Override
    public String toString() {
        return "LoanCalculationResponse{" +
                "amount=" + amount +
                ", annualInterestRate=" + annualInterestRate +
                ", numMonths=" + numMonths +
                ", paymentSchedule=" + paymentSchedule +
                '}';
    }
}
