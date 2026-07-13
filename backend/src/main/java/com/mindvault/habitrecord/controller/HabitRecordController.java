package com.mindvault.habitrecord.controller;

import com.mindvault.habitrecord.dto.CreateHabitRecordRequest;
import com.mindvault.habitrecord.dto.HabitRecordResponse;
import com.mindvault.habitrecord.dto.HabitStreakResponse;
import com.mindvault.habitrecord.service.HabitRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits/{habitId}/records")
@RequiredArgsConstructor
public class HabitRecordController {

    private final HabitRecordService habitRecordService;

    @GetMapping
    public List<HabitRecordResponse> findAll(
        @PathVariable UUID habitId,
        Authentication authentication
    ) {
        System.out.println(">>> GET HISTORY CONTROLLER <<<");

        return habitRecordService.findAllByHabit(
            habitId,
            authentication.getName()
        );
    }

    @GetMapping("/streak")
    public HabitStreakResponse getStreak(

        @PathVariable UUID habitId,

        Authentication authentication

    ) {

        System.out.println(">>> STREAK CONTROLLER <<<");

        return habitRecordService.getStreak(
            habitId,
            authentication.getName()
        );

    }

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
