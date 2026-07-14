package com.mindvault.project.dto;

import com.mindvault.project.entity.ProjectPriority;
import com.mindvault.project.entity.ProjectStatus;

import java.util.UUID;

public record ProjectResponse(

    UUID id,

    String title,

    String description,

    ProjectStatus status,

    ProjectPriority priority

) {
}
