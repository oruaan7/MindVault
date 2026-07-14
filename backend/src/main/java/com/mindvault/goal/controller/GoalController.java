package com.mindvault.goal.controller;

import com.mindvault.goal.dto.CreateGoalRequest;
import com.mindvault.goal.dto.GoalResponse;
import com.mindvault.goal.dto.UpdateGoalProgressRequest;
import com.mindvault.goal.dto.UpdateGoalRequest;
import com.mindvault.goal.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public GoalResponse create(
        @Valid @RequestBody CreateGoalRequest request,
        Authentication authentication
    ) {
        return goalService.create(request, authentication.getName());
    }

    @GetMapping
    public List<GoalResponse> findAll(Authentication authentication) {
        return goalService.findAll(authentication.getName());
    }

    @PutMapping("/{id}")
    public GoalResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateGoalRequest request,
        Authentication authentication
    ) {
        return goalService.update(id, request, authentication.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable UUID id,
        Authentication authentication
    ) {
        goalService.delete(id, authentication.getName());
    }

    @PatchMapping("/{id}/progress")
    public GoalResponse updateProgress(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateGoalProgressRequest request,
        Authentication authentication
    ) {
        return goalService.updateProgress(id, request, authentication.getName());
    }
}
