package com.mindvault.habit.controller;

import com.mindvault.habit.dto.CreateHabitRequest;
import com.mindvault.habit.dto.HabitResponse;
import com.mindvault.habit.dto.UpdateHabitRequest;
import com.mindvault.habit.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
