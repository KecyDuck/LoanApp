package com.example.loanapp.dto.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoanResponseDTO {
    private String loanId;
    private String status;
    private String message;
}
