package com.mindvault.finance.dto;

import com.mindvault.finance.entity.TransactionCategory;
import com.mindvault.finance.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionRequest(

    @NotBlank
    String title,

    String description,

    @NotNull
    BigDecimal amount,

    @NotNull
    TransactionType type,

    @NotNull
    TransactionCategory category,

    @NotNull
    LocalDate transactionDate

) {
}
