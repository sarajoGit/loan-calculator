package com.example.loancalculator.dto;

import java.math.BigDecimal;

public class PaymentSchedule {

    private int periodNumber;
    private BigDecimal paymentAmount;
    private BigDecimal principalAmount;
    private BigDecimal interestAmount;
    private BigDecimal balanceOwed;

    public PaymentSchedule() {
    }

    public PaymentSchedule(int periodNumber, BigDecimal paymentAmount, BigDecimal principalAmount, BigDecimal interestAmount, BigDecimal balanceOwed) {
        this.periodNumber = periodNumber;
        this.paymentAmount = paymentAmount;
        this.principalAmount = principalAmount;
        this.interestAmount = interestAmount;
        this.balanceOwed = balanceOwed;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
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

    @Override
    public String toString() {
        return "PaymentSchedule{" +
                "periodNumber=" + periodNumber +
                ", paymentAmount=" + paymentAmount +
                ", principalAmount=" + principalAmount +
                ", interestAmount=" + interestAmount +
                ", balanceOwed=" + balanceOwed +
                '}';
    }
}
