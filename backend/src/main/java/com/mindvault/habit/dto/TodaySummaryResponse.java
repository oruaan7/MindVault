package com.mindvault.habit.dto;

public record TodaySummaryResponse(

    int totalHabits,

    int completedHabits,

    int pendingHabits,

    int completionPercentage

) {
}
