package com.mindvault.project.dto;

import com.mindvault.project.entity.ProjectStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateProjectStatusRequest(

    @NotNull
    ProjectStatus status

) {
}
