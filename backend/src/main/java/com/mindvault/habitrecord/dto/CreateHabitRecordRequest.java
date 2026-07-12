package com.mindvault.habitrecord.dto;

import jakarta.validation.constraints.NotNull;

public record CreateHabitRecordRequest(

    @NotNull
    Boolean completed

) {
}
