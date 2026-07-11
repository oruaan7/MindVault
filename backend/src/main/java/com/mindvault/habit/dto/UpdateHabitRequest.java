package com.mindvault.habit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateHabitRequest(

    @NotBlank(message = "Title is required")
    @Size(max = 120)
    String title,

    @Size(max = 500)
    String description,

    boolean active

) {
}
