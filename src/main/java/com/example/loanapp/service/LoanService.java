package com.example.loanapp.service;

import com.example.loanapp.dto.approval.ApprovalRequestDTO;
import com.example.loanapp.dto.loan.LoanHistoryDTO;
import com.example.loanapp.dto.loan.LoanRequestDTO;
import com.example.loanapp.dto.loan.LoanResponseDTO;
import com.example.loanapp.dto.loan.LoanResponseDataDTO;
import com.example.loanapp.model.LoanData;
import com.example.loanapp.model.WFApprovalData;
import com.example.loanapp.repository.LoanDataRepository;
import com.example.loanapp.repository.WFApprovalDataRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanDataRepository loanDataRepository;

    @Autowired
    WFApprovalDataRepository approvalDataRepository;

    @Transactional
    public LoanResponseDTO postLoanRequest(LoanRequestDTO request) {
        LoanData loan = new LoanData();
        loan.setUserId(request.getUserId());
        loan.setAmount(request.getAmount());
        loan.setPurpose(request.getPurpose());
        loan.setGuarantee(request.getGuarantee());

        loanDataRepository.save(loan);

        WFApprovalData approvalData = new WFApprovalData();
        approvalData.setLoanId(loan.getId());
        approvalData.setStatus("NEW");
        approvalData.setApproverName("ADMIN 1"); //disini saya langsung tembak saja untuk approver nya
        // (in real case ini harus nya kalau bukan approverId maka akan ke workflow untuk cari siapa approver nya
        approvalDataRepository.save(approvalData);

        return new LoanResponseDTO(loan.getId(), "NEW", "Loan Request Submitted successfully");
    }

    @Transactional
    public Page<LoanResponseDataDTO> getAllLoans(Pageable pageable) {
        Page<Object[]> loanPage = loanDataRepository.findAllLoanWithStatus(pageable);

        return loanPage.map(row -> new LoanResponseDataDTO(
                (String) row[0], // id
                (String) row[1], // userName
                row[2] != null ? new BigDecimal(row[2].toString()) : null, // amount
                (String) row[3], // purpose
                (String) row[4], // guarantee
                (String) row[5], // approverName
                (String) row[6]  // status
        ));
    }

    @Transactional
    public LoanResponseDTO approveOrRejectLoan(String loanId, ApprovalRequestDTO dto) {
        WFApprovalData wf = approvalDataRepository.findByLoanId(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        wf.setStatus(dto.getAction());
        wf.setComment(dto.getComment());
        wf.setApprovalDate(java.time.LocalDateTime.now());
        approvalDataRepository.save(wf);

        return new LoanResponseDTO(loanId, dto.getAction(), "Loan approval successfully " + dto.getAction().toLowerCase());
    }

    public List<LoanHistoryDTO> getUserLoanHistory(String userId) {
        List<Object[]> rawList = loanDataRepository.findApprovedLoanHistory(userId);

        return rawList.stream()
                .map(row -> new LoanHistoryDTO(
                        (String) row[0],
                        row[1] != null ? new BigDecimal(row[1].toString()) : null,
                        (String) row[2],
                        (String) row[3],
                        (String) row[4],
                        row[5] != null ? ((Timestamp) row[5]).toLocalDateTime() : null
                ))
                .toList();
    }

}
