package com.example.loanapp.dto.approval;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalRequestDTO {
    private String approvalId;
    private String action;
    private String comment;
}
