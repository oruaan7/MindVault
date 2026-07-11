package com.mindvault.habit.repository;

import com.mindvault.habit.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HabitRepository extends JpaRepository<Habit, UUID> {
}
