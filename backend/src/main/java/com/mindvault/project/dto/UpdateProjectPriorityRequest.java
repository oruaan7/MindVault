package com.mindvault.project.dto;

import com.mindvault.project.entity.ProjectPriority;
import jakarta.validation.constraints.NotNull;

public record UpdateProjectPriorityRequest(

    @NotNull
    ProjectPriority priority

) {
}
