package com.mindvault.finance.dto;

import java.math.BigDecimal;

public record TransactionSummaryResponse(

    long totalTransactions,

    BigDecimal largestIncome,

    BigDecimal largestExpense,

    BigDecimal averageIncome,

    BigDecimal averageExpense

) {
}
