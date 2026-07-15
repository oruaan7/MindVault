package com.mindvault.habit.repository;

import com.mindvault.habit.entity.Habit;
import com.mindvault.user.entity.User;
import java.util.List;
import com.mindvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface HabitRepository extends JpaRepository<Habit, UUID> {

    List<Habit> findByUser(User user);
    List<Habit> findAllByUserOrderByCreatedAtAsc(User user);
    long countByUserAndActiveTrue(User user);
    long countByUser(User user);
}
