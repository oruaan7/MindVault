package com.mindvault.analytics.dto;

import java.math.BigDecimal;

public record FinanceAnalyticsResponse(

    BigDecimal balance,

    BigDecimal totalIncome,

    BigDecimal totalExpense,

    long incomeTransactions,

    long expenseTransactions

) {
}
