package com.example.loanapp.repository;

import com.example.loanapp.model.WFApprovalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WFApprovalDataRepository extends JpaRepository<WFApprovalData, String> {

    Optional<WFApprovalData> findByLoanId(String loanId);
}
