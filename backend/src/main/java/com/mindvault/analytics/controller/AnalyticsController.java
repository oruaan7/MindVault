package com.mindvault.analytics.controller;

import com.mindvault.analytics.dto.*;
import com.mindvault.analytics.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
@Tag(
    name = "Analytics",
    description = "Endpoints responsáveis pelos indicadores e estatísticas do usuário."
)
@SecurityRequirement(name = "bearerAuth")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Operation(
        summary = "Dashboard Geral",
        description = "Retorna os principais indicadores gerais do usuário autenticado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dashboard retornado com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")
    })
    @GetMapping("/dashboard")
    public DashboardResponse dashboard(
        Authentication authentication
    ) {

        return analyticsService.dashboard(
            authentication.getName()
        );

    }

    @Operation(
        summary = "Analytics de Hábitos",
        description = "Retorna estatísticas relacionadas aos hábitos do usuário."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estatísticas retornadas com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")
    })
    @GetMapping("/habits")
    public HabitAnalyticsResponse habits(
        Authentication authentication
    ) {

        return analyticsService.habits(
            authentication.getName()
        );

    }

    @Operation(
        summary = "Analytics Financeiro",
        description = "Retorna indicadores financeiros do usuário."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Indicadores financeiros retornados com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")
    })
    @GetMapping("/finance")
    public FinanceAnalyticsResponse finance(
        Authentication authentication
    ) {

        return analyticsService.finance(
            authentication.getName()
        );

    }

    @Operation(
        summary = "Analytics Consolidado",
        description = "Retorna um dashboard consolidado contendo indicadores gerais, hábitos e finanças."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Analytics consolidado retornado com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")
    })
    @GetMapping
    public AnalyticsResponse analytics(
        Authentication authentication
    ) {

        return analyticsService.analytics(
            authentication.getName()
        );

    }

    @Operation(
        summary = "Analytics de Metas",
        description = "Retorna estatísticas sobre o progresso das metas do usuário."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estatísticas de metas retornadas com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")
    })
    @GetMapping("/goals")
    public GoalAnalyticsResponse goals(
        Authentication authentication
    ) {

        return analyticsService.goals(
            authentication.getName()
        );

    }

    @Operation(
        summary = "Analytics de Projetos",
        description = "Retorna estatísticas sobre os projetos do usuário."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estatísticas de projetos retornadas com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")
    })
    @GetMapping("/projects")
    public ProjectAnalyticsResponse projects(
        Authentication authentication
    ) {

        return analyticsService.projects(
            authentication.getName()
        );

    }

    @Operation(
        summary = "Dashboard de Produtividade",
        description = "Retorna indicadores consolidados de produtividade com base em hábitos, metas e projetos."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Indicadores de produtividade retornados com sucesso."),
        @ApiResponse(responseCode = "401", description = "Usuário não autenticado.")
    })
    @GetMapping("/productivity")
    public ProductivityResponse productivity(
        Authentication authentication
    ) {

        return analyticsService.productivity(
            authentication.getName()
        );

    }

}
