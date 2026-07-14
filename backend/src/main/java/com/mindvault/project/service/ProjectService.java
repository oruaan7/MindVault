package com.mindvault.project.service;

import com.mindvault.project.dto.*;
import com.mindvault.project.entity.Project;
import com.mindvault.project.entity.ProjectPriority;
import com.mindvault.project.entity.ProjectStatus;
import com.mindvault.project.repository.ProjectRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectResponse create(
        CreateProjectRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Project project = new Project();

        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setStatus(ProjectStatus.TODO);
        project.setPriority(ProjectPriority.MEDIUM);
        project.setUser(user);

        return map(projectRepository.save(project));

    }

    public List<ProjectResponse> findAll(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return projectRepository
            .findAllByUserOrderByCreatedAtDesc(user)
            .stream()
            .map(this::map)
            .toList();

    }

    private ProjectResponse map(Project project) {

        return new ProjectResponse(
            project.getId(),
            project.getTitle(),
            project.getDescription(),
            project.getStatus(),
            project.getPriority()
        );

    }

    public ProjectResponse update(
        UUID id,
        UpdateProjectRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Project project = projectRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Project not found"));

        validateOwnership(project, user);

        project.setTitle(request.title());
        project.setDescription(request.description());

        return map(projectRepository.save(project));

    }

    public void delete(
        UUID id,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Project project = projectRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Project not found"));

        validateOwnership(project, user);

        projectRepository.delete(project);

    }

    private void validateOwnership(
        Project project,
        User user
    ) {

        if (!project.getUser().getId().equals(user.getId())) {

            throw new IllegalArgumentException(
                "Project does not belong to user"
            );

        }

    }

    public ProjectResponse updateStatus(
        UUID id,
        UpdateProjectStatusRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Project project = projectRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Project not found"));

        validateOwnership(project, user);

        project.setStatus(request.status());

        Project updatedProject = projectRepository.save(project);

        return map(updatedProject);

    }

    public ProjectResponse updatePriority(
        UUID id,
        UpdateProjectPriorityRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Project project = projectRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Project not found"));

        validateOwnership(project, user);

        project.setPriority(request.priority());

        Project updatedProject = projectRepository.save(project);

        return map(updatedProject);

    }

    public List<ProjectResponse> search(
        String query,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return projectRepository
            .findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCaseOrderByCreatedAtDesc(
                user,
                query,
                user,
                query
            )
            .stream()
            .map(this::map)
            .toList();

    }

}
