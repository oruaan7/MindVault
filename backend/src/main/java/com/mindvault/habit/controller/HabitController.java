package com.mindvault.habit.controller;

import com.mindvault.habit.dto.HabitStreakResponse;
import com.mindvault.habit.dto.*;
import com.mindvault.habit.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public HabitResponse create(
        @Valid @RequestBody CreateHabitRequest request,
        Authentication authentication
    ) {
        return habitService.create(request, authentication.getName());
    }

    @GetMapping
    public List<HabitResponse> findAll(Authentication authentication) {
        return habitService.findAll(authentication.getName());
    }

    @PutMapping("/{id}")
    public HabitResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateHabitRequest request,
        Authentication authentication
    ) {
        return habitService.update(id, request, authentication.getName());
    }

    @GetMapping("/{id}/streak")
    public HabitStreakResponse streak(
        @PathVariable UUID id,
        Authentication authentication
    ) {
        System.out.println(">>> STREAK CONTROLLER <<<");
        return habitService.streak(
            id,
            authentication.getName()
        );

    }

    @GetMapping("/today/summary")
    public TodaySummaryResponse todaySummary(
        Authentication authentication
    ) {
        System.out.println(">>> TODAY SUMMARY CONTROLLER <<<");
        return habitService.todaySummary(
            authentication.getName()
        );

    }

    @GetMapping("/today")
    public List<TodayHabitResponse> today(
        Authentication authentication
    ) {
        System.out.println(">>> TODAY CONTROLLER <<<");
        return habitService.findToday(
            authentication.getName()
        );

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable UUID id,
        Authentication authentication
    ) {
        System.out.println(">>> DELETE chegou ao controller <<<");
        habitService.delete(id, authentication.getName());
    }
}
