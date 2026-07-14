package com.mindvault.finance.dto;

import com.mindvault.finance.entity.TransactionCategory;
import com.mindvault.finance.entity.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponse(

    UUID id,

    String title,

    String description,

    BigDecimal amount,

    TransactionType type,

    TransactionCategory category,

    LocalDate transactionDate

) {
}
