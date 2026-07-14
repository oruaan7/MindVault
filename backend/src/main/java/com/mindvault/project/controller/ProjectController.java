package com.mindvault.project.controller;

import com.mindvault.project.dto.CreateProjectRequest;
import com.mindvault.project.dto.ProjectResponse;
import com.mindvault.project.dto.UpdateProjectRequest;
import com.mindvault.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectResponse create(
        @Valid
        @RequestBody
        CreateProjectRequest request,
        Authentication authentication
    ) {

        return projectService.create(
            request,
            authentication.getName()
        );

    }

    @GetMapping
    public List<ProjectResponse> findAll(
        Authentication authentication
    ) {

        return projectService.findAll(
            authentication.getName()
        );

    }

    @PutMapping("/{id}")
    public ProjectResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProjectRequest request,
        Authentication authentication
    ) {

        return projectService.update(
            id,
            request,
            authentication.getName()
        );

    }

}
