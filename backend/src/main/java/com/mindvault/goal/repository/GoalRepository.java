package com.mindvault.goal.repository;

import com.mindvault.goal.entity.Goal;
import com.mindvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID> {

    List<Goal> findAllByUserOrderByCreatedAtAsc(User user);

}
