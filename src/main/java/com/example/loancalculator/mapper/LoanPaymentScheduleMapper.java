package com.example.loancalculator.mapper;

import com.example.loancalculator.dto.PaymentSchedule;
import com.example.loancalculator.model.LoanPaymentSchedule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanPaymentScheduleMapper {

    LoanPaymentSchedule toEntity(PaymentSchedule paymentSchedule);

    List<LoanPaymentSchedule> toEntityList(List<PaymentSchedule> paymentScheduleList);

    PaymentSchedule toDTO(LoanPaymentSchedule paymentSchedule);

    List<PaymentSchedule> toDTOList(List<LoanPaymentSchedule> paymentScheduleList);
}
