package com.mindvault.project.service;

import com.mindvault.project.dto.CreateProjectRequest;
import com.mindvault.project.dto.ProjectResponse;
import com.mindvault.project.entity.Project;
import com.mindvault.project.entity.ProjectPriority;
import com.mindvault.project.entity.ProjectStatus;
import com.mindvault.project.repository.ProjectRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        Project savedProject = projectRepository.save(project);

        return map(savedProject);

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

}
