package com.mindvault.analytics.controller;

import com.mindvault.analytics.dto.*;
import com.mindvault.analytics.dto.ProjectAnalyticsResponse;
import com.mindvault.analytics.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public DashboardResponse dashboard(
        Authentication authentication
    ) {

        return analyticsService.dashboard(
            authentication.getName()
        );

    }

    @GetMapping("/habits")
    public HabitAnalyticsResponse habits(
        Authentication authentication
    ) {

        return analyticsService.habits(
            authentication.getName()
        );

    }

    @GetMapping("/finance")
    public FinanceAnalyticsResponse finance(
        Authentication authentication
    ) {

        return analyticsService.finance(
            authentication.getName()
        );

    }

    @GetMapping
    public AnalyticsResponse analytics(
        Authentication authentication
    ) {

        return analyticsService.analytics(
            authentication.getName()
        );

    }

    @GetMapping("/goals")
    public GoalAnalyticsResponse goals(
        Authentication authentication
    ) {

        return analyticsService.goals(
            authentication.getName()
        );

    }

    @GetMapping("/projects")
    public ProjectAnalyticsResponse projects(
        Authentication authentication
    ) {

        return analyticsService.projects(
            authentication.getName()
        );

    }

}
