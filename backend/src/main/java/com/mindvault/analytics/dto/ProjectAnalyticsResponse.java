package com.mindvault.analytics.dto;

import java.math.BigDecimal;

public record ProjectAnalyticsResponse(

    long totalProjects,

    long completedProjects,

    long inProgressProjects,

    long todoProjects,

    long onHoldProjects,

    BigDecimal completionRate

) {
}
