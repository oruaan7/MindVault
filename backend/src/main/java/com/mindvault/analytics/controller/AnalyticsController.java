package com.mindvault.analytics.controller;

import com.mindvault.analytics.dto.AnalyticsResponse;
import com.mindvault.analytics.dto.DashboardResponse;
import com.mindvault.analytics.dto.FinanceAnalyticsResponse;
import com.mindvault.analytics.dto.HabitAnalyticsResponse;
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

}
