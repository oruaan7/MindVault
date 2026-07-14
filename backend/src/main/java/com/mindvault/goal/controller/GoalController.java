package com.mindvault.goal.controller;

import com.mindvault.goal.dto.CreateGoalRequest;
import com.mindvault.goal.dto.GoalResponse;
import com.mindvault.goal.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public GoalResponse create(
        @Valid
        @RequestBody
        CreateGoalRequest request,
        Authentication authentication
    ) {

        return goalService.create(
            request,
            authentication.getName()
        );

    }

    @GetMapping
    public List<GoalResponse> findAll(
        Authentication authentication
    ) {

        return goalService.findAll(
            authentication.getName()
        );

    }

}
