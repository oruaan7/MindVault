package com.mindvault.habitrecord.service;

import com.mindvault.habit.repository.HabitRepository;
import com.mindvault.habitrecord.dto.CreateHabitRecordRequest;
import com.mindvault.habitrecord.dto.HabitRecordResponse;
import com.mindvault.habitrecord.repository.HabitRecordRepository;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
