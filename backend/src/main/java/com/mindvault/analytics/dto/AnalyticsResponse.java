package com.mindvault.analytics.dto;

public record AnalyticsResponse(

    DashboardResponse dashboard,

    HabitAnalyticsResponse habits,

    FinanceAnalyticsResponse finance

) {
}
