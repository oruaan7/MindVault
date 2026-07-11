package com.mindvault.habit.dto;

import java.util.UUID;

public record HabitResponse(

    UUID id,

    String title,

    String description,

    boolean active

) {
}
