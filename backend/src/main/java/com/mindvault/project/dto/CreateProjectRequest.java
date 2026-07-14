package com.mindvault.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProjectRequest(

    @NotBlank
    @Size(max = 150)
    String title,

    String description

) {
}
