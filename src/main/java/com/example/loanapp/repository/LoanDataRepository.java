package com.example.loanapp.repository;

import com.example.loanapp.model.LoanData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanDataRepository extends JpaRepository<LoanData, String> {

    @Query(value = """
        SELECT 
            l.id AS loan_id,
            u.user_name,
            l.amount,
            l.purpose,
            l.guarantee,
            w.approver_name,
            w.status
        FROM loan_data l
        JOIN user_data u ON u.id = l.user_id
        LEFT JOIN wf_approval_data w ON w.loan_id = l.id
        """, nativeQuery = true)
    Page<Object[]> findAllLoanWithStatus(Pageable pageable);

    @Query(value = """
        SELECT l.id, l.amount, l.purpose, w.status, w.comment, w.approval_date
        FROM loan_data l
        LEFT JOIN wf_approval_data w ON w.loan_id = l.id
        WHERE l.user_id = :userId AND w.status = 'APPROVED'
        ORDER BY w.approval_date DESC
        """, nativeQuery = true)
    List<Object[]> findApprovedLoanHistory(@Param("userId") String userId);


}
