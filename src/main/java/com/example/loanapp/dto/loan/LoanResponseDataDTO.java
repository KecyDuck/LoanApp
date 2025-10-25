package com.example.loanapp.dto.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDataDTO {
    private String id;
    private String userName;
    private BigDecimal amount;
    private String purpose;
    private String guarantee;
    private String approverName;
    private String status;
}
