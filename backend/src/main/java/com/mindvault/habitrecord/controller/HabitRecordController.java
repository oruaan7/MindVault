package com.mindvault.habitrecord.controller;

import com.mindvault.habitrecord.dto.CreateHabitRecordRequest;
import com.mindvault.habitrecord.dto.HabitRecordResponse;
import com.mindvault.habitrecord.service.HabitRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits/{habitId}/records")
@RequiredArgsConstructor
public class HabitRecordController {

    private final HabitRecordService habitRecordService;

    @PostMapping
    public HabitRecordResponse create(
        @PathVariable UUID habitId,
        @Valid @RequestBody CreateHabitRecordRequest request,
        Authentication authentication
    ) {

        System.out.println(">>> CONTROLLER EXECUTADO <<<");

        return habitRecordService.create(
            habitId,
            request,
            authentication.getName()
        );
    }
}
