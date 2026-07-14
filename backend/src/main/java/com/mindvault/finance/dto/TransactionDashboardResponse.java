package com.mindvault.finance.dto;

import java.math.BigDecimal;

public record TransactionDashboardResponse(

    BigDecimal totalIncome,

    BigDecimal totalExpense,

    BigDecimal balance,

    long incomeCount,

    long expenseCount

) {
}
