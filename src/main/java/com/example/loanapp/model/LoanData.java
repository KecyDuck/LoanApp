package com.example.loanapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "loan_data")
public class LoanData extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    private UserData userData;

    @Column(name = "user_id")
    private String userId;

    private BigDecimal amount;
    private String purpose;
    private String guarantee;

}
