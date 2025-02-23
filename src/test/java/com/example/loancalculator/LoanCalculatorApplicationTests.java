package com.example.loancalculator;

import com.example.loancalculator.dto.LoanCalculationDTO;
import com.example.loancalculator.dto.LoanDetailsDTO;
import com.example.loancalculator.dto.PaymentSchedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class LoanCalculatorApplicationTests {

    private final String uri = "/api/v1/loans";
    ObjectMapper objectMapper = new ObjectMapper();
    BigDecimal amount;
    BigDecimal annualInterestRate;
    int months;
    BigDecimal expectedPayment;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        amount = BigDecimal.valueOf(1000.0);
        annualInterestRate = BigDecimal.valueOf(5.0);
        months = 10;
        expectedPayment = BigDecimal.valueOf(102.31);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testMonthlyPayments() throws Exception {
        LoanDetailsDTO request = new LoanDetailsDTO(amount, annualInterestRate, months);
        String requestBody = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        LoanCalculationDTO response = objectMapper.readValue(responseBody, LoanCalculationDTO.class);

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        for (PaymentSchedule paymentSchedule : response.getPaymentSchedule()) {
            BigDecimal actualPayment = paymentSchedule.getPaymentAmount();
            assertEquals(expectedPayment, actualPayment);
        }

    }

    @Test
    public void testNullAmount() throws Exception {
        LoanDetailsDTO request = new LoanDetailsDTO(null, annualInterestRate, months);
        String requestBody = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());

        String expectedMessage = "Loan amount must not be null";
        String actualMessage = result.getResponse().getContentAsString();
        assert (actualMessage.contains(expectedMessage));
    }


    @Test
    public void testZeroMonths() throws Exception {
        LoanDetailsDTO request = new LoanDetailsDTO(amount, annualInterestRate, 0);
        String requestBody = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());

        String expectedMessage = "Number of periods must be greater than 0";
        String actualMessage = result.getResponse().getContentAsString();
        assert (actualMessage.contains(expectedMessage));
    }

    @Test
    public void testNegativeInterestRate() throws Exception {
        LoanDetailsDTO request = new LoanDetailsDTO(amount, BigDecimal.valueOf(-1), months);
        String requestBody = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());

        String expectedMessage = "Interest rate must be greater than or equal to 0";
        String actualMessage = result.getResponse().getContentAsString();
        assert (actualMessage.contains(expectedMessage));
    }


    @Test
    public void testLoanFetch() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/loans/{loanId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testLoanNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/loans/{loanId}", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    public void testPaymentFetch() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/loans/{loanId}/payments", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testPaymentNotFound() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/loans/{loanId}/payments", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

}
