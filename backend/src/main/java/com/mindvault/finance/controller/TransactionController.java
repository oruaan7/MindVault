package com.mindvault.finance.controller;

import com.mindvault.finance.dto.CreateTransactionRequest;
import com.mindvault.finance.dto.TransactionBalanceResponse;
import com.mindvault.finance.dto.TransactionDashboardResponse;
import com.mindvault.finance.dto.TransactionResponse;
import com.mindvault.finance.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable UUID id,
        Authentication authentication
    ) {

        transactionService.delete(
            id,
            authentication.getName()
        );

    }

    @GetMapping("/dashboard")
    public TransactionDashboardResponse dashboard(
        Authentication authentication
    ) {

        return transactionService.dashboard(
            authentication.getName()
        );

    }

    @GetMapping("/period")
    public List<TransactionResponse> findByPeriod(

        @RequestParam LocalDate startDate,

        @RequestParam LocalDate endDate,

        Authentication authentication

    ) {

        return transactionService.findByPeriod(

            startDate,

            endDate,

            authentication.getName()

        );

    }

    @GetMapping("/balance")
    public TransactionBalanceResponse balance(
        Authentication authentication
    ) {

        return transactionService.balance(
            authentication.getName()
        );

    }

}
