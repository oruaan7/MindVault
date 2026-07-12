package com.mindvault.habitrecord.repository;

import com.mindvault.habitrecord.entity.HabitRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HabitRecordRepository extends JpaRepository<HabitRecord, UUID> {
}
