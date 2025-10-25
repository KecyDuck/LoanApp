package com.example.loanapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "wf_approval_data")
public class WFApprovalData extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id",referencedColumnName = "id",insertable = false,updatable = false)
    private LoanData loanData;

    @Column(name = "loan_id")
    private String loanId;

    private String approverName;
    private String status;
    private String comment;
    private LocalDateTime approvalDate;

}
