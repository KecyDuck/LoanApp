package com.example.loanapp.dto.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanHistoryDTO {
    private String id;
    private BigDecimal amount;
    private String purpose;
    private String status;
    private String comment;
    private LocalDateTime approvalDate;
}

