package com.mindvault.analytics.dto;

public record DashboardResponse(

    long totalHabits,

    long totalGoals,

    long totalProjects,

    long totalNotes,

    long totalTransactions

) {
}
