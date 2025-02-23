package com.example.loancalculator.service;

import com.example.loancalculator.dto.LoanDetailsDTO;
import com.example.loancalculator.dto.PaymentSchedule;
import com.example.loancalculator.mapper.LoanDetailsMapper;
import com.example.loancalculator.mapper.LoanPaymentScheduleMapper;
import com.example.loancalculator.model.LoanDetails;
import com.example.loancalculator.model.LoanPaymentSchedule;
import com.example.loancalculator.repository.LoanDetailsRepository;
import com.example.loancalculator.repository.LoanPaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanDetailsService {

    private final LoanDetailsRepository loanDetailsRepository;
    private final LoanPaymentScheduleRepository loanPaymentScheduleRepository;
    private final LoanDetailsMapper loanDetailsMapper;
    private final LoanPaymentScheduleMapper loanPaymentScheduleMapper;


    @Autowired
    public LoanDetailsService(LoanDetailsRepository loanDetailsRepository, LoanPaymentScheduleRepository loanPaymentScheduleRepository, LoanDetailsMapper loanDetailsMapper, LoanPaymentScheduleMapper loanPaymentScheduleMapper) {
        this.loanDetailsRepository = loanDetailsRepository;
        this.loanPaymentScheduleRepository = loanPaymentScheduleRepository;
        this.loanDetailsMapper = loanDetailsMapper;
        this.loanPaymentScheduleMapper = loanPaymentScheduleMapper;
    }


    public LoanDetailsDTO getLoanDetails(Integer loanId) {
        Optional<LoanDetails> loanDetailsOptional = loanDetailsRepository.findById(loanId);
        return loanDetailsOptional.map(loanDetailsMapper::toDTO).orElse(null);
    }

    public List<PaymentSchedule> getLoanPaymentSchedule(Integer loanId) {
        List<LoanPaymentSchedule> loanPaymentSchedules = loanPaymentScheduleRepository.findByLoanDetailsLoanId(loanId);
        return loanPaymentScheduleMapper.toDTOList(loanPaymentSchedules);
    }

}
