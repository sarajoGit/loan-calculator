package com.example.loancalculator.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_details")
public class LoanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Integer loanId;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "annual_interest_rate", nullable = false)
    private BigDecimal annualInterestRate;
    @Column(name = "term_in_months", nullable = false)
    private Integer termInMonths;

    public LoanDetails() {
    }

    public LoanDetails(BigDecimal amount, BigDecimal annualInterestRate, Integer termInMonths) {
        this.amount = amount;
        this.annualInterestRate = annualInterestRate;
        this.termInMonths = termInMonths;
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

    public Integer getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
    }
}

