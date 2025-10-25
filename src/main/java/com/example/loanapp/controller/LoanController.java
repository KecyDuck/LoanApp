package com.example.loanapp.controller;

import com.example.loanapp.dto.approval.ApprovalRequestDTO;
import com.example.loanapp.dto.loan.LoanHistoryDTO;
import com.example.loanapp.dto.loan.LoanRequestDTO;
import com.example.loanapp.dto.loan.LoanResponseDTO;
import com.example.loanapp.dto.loan.LoanResponseDataDTO;
import com.example.loanapp.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    //using custom response
    @PostMapping("/request")
    public LoanResponseDTO postLoanRequest(@RequestBody LoanRequestDTO request) {
        return loanService.postLoanRequest(request);
    }

    @GetMapping
    public Page<LoanResponseDataDTO> getAllLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return loanService.getAllLoans(PageRequest.of(page, size));
    }

    @PostMapping("/{loanId}/approval")
    public LoanResponseDTO approveOrRejectLoan(@PathVariable String loanId, @RequestBody ApprovalRequestDTO dto) {
        return loanService.approveOrRejectLoan(loanId, dto);
    }

    @GetMapping("/history/{userId}")
    public List<LoanHistoryDTO> getUserLoanHistory(@PathVariable String userId) {
        return loanService.getUserLoanHistory(userId);
    }

}
