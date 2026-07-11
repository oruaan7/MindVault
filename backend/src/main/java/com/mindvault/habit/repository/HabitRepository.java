package com.mindvault.habit.repository;

import com.mindvault.habit.entity.Habit;
import com.mindvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HabitRepository extends JpaRepository<Habit, UUID> {

    List<Habit> findByUser(User user);

}
