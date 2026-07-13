package com.mindvault.habit.dto;

import java.util.UUID;

public record TodayHabitResponse(

    UUID id,

    String title,

    boolean completed

) {
}
