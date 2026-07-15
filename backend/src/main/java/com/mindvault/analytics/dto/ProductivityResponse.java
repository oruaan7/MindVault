package com.mindvault.analytics.dto;

import java.math.BigDecimal;

public record ProductivityResponse(

    BigDecimal habitCompletionRate,

    BigDecimal goalCompletionRate,

    BigDecimal projectCompletionRate,

    BigDecimal overallCompletionRate

) {
}
