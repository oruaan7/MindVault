package com.mindvault.project.repository;

import com.mindvault.project.entity.Project;
import com.mindvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findAllByUserOrderByCreatedAtDesc(User user);

    List<Project> findByUserAndTitleContainingIgnoreCaseOrUserAndDescriptionContainingIgnoreCaseOrderByCreatedAtDesc(
        User user,
        String title,
        User sameUser,
        String description
    );

}
