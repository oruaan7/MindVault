package com.mindvault.habitrecord.dto;

import java.time.LocalDate;
import java.util.UUID;

public record HabitRecordResponse(

    UUID id,

    LocalDate date,

    boolean completed

) {
}
