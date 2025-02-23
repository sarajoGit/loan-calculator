package com.example.loancalculator.mapper;


import com.example.loancalculator.dto.LoanDetailsDTO;
import com.example.loancalculator.model.LoanDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanDetailsMapper {

    @Mapping(source = "termInMonths", target = "months")
    LoanDetailsDTO toDTO(LoanDetails loanDetails);

}
