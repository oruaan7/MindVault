package com.mindvault.habitrecord.service;

import com.mindvault.habit.repository.HabitRepository;
import com.mindvault.habitrecord.dto.CreateHabitRecordRequest;
import com.mindvault.habitrecord.dto.HabitRecordResponse;
import com.mindvault.habitrecord.repository.HabitRecordRepository;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mindvault.habit.entity.Habit;
import com.mindvault.habitrecord.entity.HabitRecord;
import com.mindvault.user.entity.User;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HabitRecordService {

    private final HabitRecordRepository habitRecordRepository;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitRecordResponse create(
        UUID habitId,
        CreateHabitRecordRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Habit habit = habitRepository.findById(habitId)
            .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Habit does not belong to user");
        }

        LocalDate today = LocalDate.now();

        HabitRecord habitRecord = habitRecordRepository
            .findByHabitAndDate(habit, today)
            .orElseGet(HabitRecord::new);

        habitRecord.setHabit(habit);
        habitRecord.setDate(today);
        habitRecord.setCompleted(request.completed());

        HabitRecord saved = habitRecordRepository.save(habitRecord);

        return new HabitRecordResponse(
            saved.getId(),
            saved.getDate(),
            saved.isCompleted()
        );
    }

    public List<HabitRecordResponse> findAllByHabit(
        UUID habitId,
        String email
    ) {
        System.out.println(">>> HISTORY SERVICE <<<");

        throw new UnsupportedOperationException("Not implemented yet");
    }
}
