package com.mindvault.finance.controller;

import com.mindvault.finance.dto.CreateTransactionRequest;
import com.mindvault.finance.dto.TransactionResponse;
import com.mindvault.finance.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponse create(
        @Valid
        @RequestBody
        CreateTransactionRequest request,
        Authentication authentication
    ) {

        return transactionService.create(
            request,
            authentication.getName()
        );

    }

}
