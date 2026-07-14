package com.mindvault.goal.dto;

import jakarta.validation.constraints.Min;

public record UpdateGoalProgressRequest(

    @Min(0)
    int currentValue

) {
}
