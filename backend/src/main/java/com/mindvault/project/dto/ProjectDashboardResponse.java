package com.mindvault.project.dto;

public record ProjectDashboardResponse(

    long totalProjects,

    long todoProjects,

    long inProgressProjects,

    long doneProjects,

    long onHoldProjects,

    long lowPriorityProjects,

    long mediumPriorityProjects,

    long highPriorityProjects,

    long criticalPriorityProjects

) {
}
