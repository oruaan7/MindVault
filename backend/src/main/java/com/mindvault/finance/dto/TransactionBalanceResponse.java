package com.mindvault.finance.dto;

import java.math.BigDecimal;

public record TransactionBalanceResponse(

    BigDecimal balance

) {
}
