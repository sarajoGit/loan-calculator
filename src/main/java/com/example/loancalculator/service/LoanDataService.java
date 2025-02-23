package com.example.loancalculator.service;

import com.example.loancalculator.model.LoanDetails;
import com.example.loancalculator.model.LoanPaymentSchedule;
import com.example.loancalculator.repository.LoanDetailsRepository;
import com.example.loancalculator.repository.LoanPaymentScheduleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanDataService {
    private static final Logger log = LoggerFactory.getLogger(LoanDataService.class);

    private final LoanDetailsRepository loanDetailsRepository;
    private final LoanPaymentScheduleRepository loanPaymentScheduleRepository;

    @Autowired
    public LoanDataService(LoanDetailsRepository loanDetailsRepository, LoanPaymentScheduleRepository loanPaymentScheduleRepository) {
        this.loanDetailsRepository = loanDetailsRepository;
        this.loanPaymentScheduleRepository = loanPaymentScheduleRepository;
    }

    @Transactional
    public LoanDetails saveLoanAndPayments(LoanDetails loanDetails, List<LoanPaymentSchedule> paymentSchedules) {
        try {
            LoanDetails persistedLoanDetails = loanDetailsRepository.save(loanDetails);
            log.info("Successfully persisted LoanDetails with ID {}", persistedLoanDetails.getLoanId());

            for (LoanPaymentSchedule paymentSchedule : paymentSchedules) {
                paymentSchedule.setLoanDetails(persistedLoanDetails);
                loanPaymentScheduleRepository.save(paymentSchedule);
            }
            log.info("Successfully persisted LoanPaymentSchedule for loanID {}", persistedLoanDetails.getLoanId());
            return persistedLoanDetails;
        } catch (Exception e) {
            log.error("Error occurred while saving loan and payments: {}", e.getMessage());
            throw e;
        }
    }
}
