package com.mindvault.analytics.dto;

import java.math.BigDecimal;

public record HabitAnalyticsResponse(

    long totalHabits,

    long activeHabits,

    long completedToday,

    BigDecimal completionRate

) {
}
