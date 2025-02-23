package com.example.loancalculator.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_payment_schedule")
public class LoanPaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;
    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private LoanDetails loanDetails;
    @Column(name = "period_number", nullable = false)
    private Integer periodNumber;
    @Column(name = "payment_amount", nullable = false)
    private BigDecimal paymentAmount;
    @Column(name = "principal_amount", nullable = false)
    private BigDecimal principalAmount;
    @Column(name = "interest_amount", nullable = false)
    private BigDecimal interestAmount;
    @Column(name = "balance_owed", nullable = false)
    private BigDecimal balanceOwed;

    public LoanPaymentSchedule() {
    }

    public LoanPaymentSchedule(Integer periodNumber, BigDecimal paymentAmount,
                               BigDecimal principalAmount, BigDecimal interestAmount, BigDecimal balanceOwed) {
        this.periodNumber = periodNumber;
        this.paymentAmount = paymentAmount;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
        this.balanceOwed = balanceOwed;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public LoanDetails getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(LoanDetails loanDetails) {
        this.loanDetails = loanDetails;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getBalanceOwed() {
        return balanceOwed;
    }

    public void setBalanceOwed(BigDecimal balanceOwed) {
        this.balanceOwed = balanceOwed;
    }

}

