package com.mindvault.finance.controller;

import com.mindvault.finance.dto.CreateTransactionRequest;
import com.mindvault.finance.dto.TransactionResponse;
import com.mindvault.finance.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<TransactionResponse> findAll(
        Authentication authentication
    ) {

        return transactionService.findAll(
            authentication.getName()
        );

    }

    @PutMapping("/{id}")
    public TransactionResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody CreateTransactionRequest request,
        Authentication authentication
    ) {

        return transactionService.update(
            id,
            request,
            authentication.getName()
        );

    }


}
