package com.mindvault.analytics.dto;

import java.math.BigDecimal;

public record GoalAnalyticsResponse(

    long totalGoals,

    long completedGoals,

    long pendingGoals,

    BigDecimal completionRate

) {
}
