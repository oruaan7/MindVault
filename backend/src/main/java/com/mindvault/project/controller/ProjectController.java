package com.mindvault.project.controller;

import com.mindvault.project.dto.*;
import com.mindvault.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable UUID id,
        Authentication authentication
    ) {

        projectService.delete(
            id,
            authentication.getName()
        );

    }

    @PatchMapping("/{id}/status")
    public ProjectResponse updateStatus(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProjectStatusRequest request,
        Authentication authentication
    ) {

        return projectService.updateStatus(
            id,
            request,
            authentication.getName()
        );

    }

    @PatchMapping("/{id}/priority")
    public ProjectResponse updatePriority(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateProjectPriorityRequest request,
        Authentication authentication
    ) {

        return projectService.updatePriority(
            id,
            request,
            authentication.getName()
        );

    }

    @GetMapping("/search")
    public List<ProjectResponse> search(
        @RequestParam String q,
        Authentication authentication
    ) {

        return projectService.search(
            q,
            authentication.getName()
        );

    }

}
