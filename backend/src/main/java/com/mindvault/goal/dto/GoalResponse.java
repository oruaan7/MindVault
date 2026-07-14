package com.mindvault.goal.dto;

import java.util.UUID;

public record GoalResponse(

    UUID id,

    String title,

    String description,

    int targetValue,

    int currentValue,

    boolean completed

) {
}
