package com.example.loancalculator.service;

import com.example.loancalculator.dto.LoanCalculationDTO;
import com.example.loancalculator.dto.PaymentSchedule;
import com.example.loancalculator.mapper.LoanPaymentScheduleMapper;
import com.example.loancalculator.model.LoanDetails;
import com.example.loancalculator.model.LoanPaymentSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanCalculatorService {
    private static final Logger log = LoggerFactory.getLogger(LoanCalculatorService.class);

    private final LoanDataService loanDataService;
    private final LoanPaymentScheduleMapper loanPaymentScheduleMapper;

    @Autowired
    public LoanCalculatorService(LoanDataService loanDataService, LoanPaymentScheduleMapper loanPaymentScheduleMapper) {
        this.loanDataService = loanDataService;
        this.loanPaymentScheduleMapper = loanPaymentScheduleMapper;
    }


    public LoanCalculationDTO calculateLoan(BigDecimal amount, BigDecimal annualInterestRate, Integer months) {
        try {
            BigDecimal monthlyPayment = calculateMonthlyPayment(amount, annualInterestRate, months);

            log.info("Calculated monthlyPayment {} for amount={}, annualInterestRate={}, months={}",
                    monthlyPayment, amount, annualInterestRate, months);
            List<PaymentSchedule> paymentScheduleList = calculatePaymentSchedule(amount, annualInterestRate, months, monthlyPayment);

            List<LoanPaymentSchedule> loanPaymentSchedules = loanPaymentScheduleMapper.toEntityList(paymentScheduleList);
            LoanDetails persistedLoanDetails = loanDataService.saveLoanAndPayments(new LoanDetails(amount, annualInterestRate, months), loanPaymentSchedules);

            return new LoanCalculationDTO(persistedLoanDetails.getLoanId(), amount, annualInterestRate, months, paymentScheduleList);
        } catch (Exception e) {
            log.error("Error occurred while calculating loan for amount={}, interestRate={}, months={}: {}",
                    amount, annualInterestRate, months, e.getMessage());
            throw new RuntimeException("Loan calculation failed", e);
        }
    }


    private BigDecimal calculateMonthlyPayment(BigDecimal amount, BigDecimal annualInterestRate, int months) {
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        //calculate the monthly payment using the amortization formula
        BigDecimal numerator = monthlyInterestRate.multiply(BigDecimal.ONE.add(monthlyInterestRate).pow(months));
        BigDecimal denominator = BigDecimal.ONE.add(monthlyInterestRate).pow(months).subtract(BigDecimal.ONE);
        return amount.multiply(numerator).divide(denominator, 2, RoundingMode.HALF_UP);
    }

    private List<PaymentSchedule> calculatePaymentSchedule(BigDecimal amount, BigDecimal annualInterestRate, Integer months, BigDecimal monthlyPayment) {
        BigDecimal remainingBalance = amount;
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();

        for (int i = 1; i <= months; i++) {

            BigDecimal interestAmount = remainingBalance.multiply(annualInterestRate)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);

            BigDecimal principalAmount = monthlyPayment.subtract(interestAmount);
            remainingBalance = remainingBalance.subtract(principalAmount);

            if (remainingBalance.abs().compareTo(BigDecimal.valueOf(0.1)) <= 0) {
                remainingBalance = BigDecimal.ZERO;
            }

            PaymentSchedule paymentSchedule = new PaymentSchedule(i, monthlyPayment, principalAmount, interestAmount, remainingBalance);
            paymentScheduleList.add(paymentSchedule);
        }

        return paymentScheduleList;
    }

}


