package com.mindvault.habitrecord.repository;

import java.util.List;
import com.mindvault.habit.entity.Habit;
import com.mindvault.habitrecord.entity.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface HabitRecordRepository extends JpaRepository<HabitRecord, UUID> {

    Optional<HabitRecord> findByHabitAndDate(Habit habit, LocalDate date);

    List<HabitRecord> findAllByHabitOrderByDateDesc(Habit habit);

}
