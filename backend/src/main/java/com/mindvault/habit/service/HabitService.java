package com.mindvault.habit.service;

import com.mindvault.habit.dto.CreateHabitRequest;
import com.mindvault.habit.dto.HabitResponse;
import com.mindvault.habit.entity.Habit;
import com.mindvault.habit.repository.HabitRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitResponse create(CreateHabitRequest request, String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Habit habit = new Habit();
        habit.setTitle(request.title());
        habit.setDescription(request.description());
        habit.setUser(user);

        Habit savedHabit = habitRepository.save(habit);

        return new HabitResponse(
            savedHabit.getId(),
            savedHabit.getTitle(),
            savedHabit.getDescription(),
            savedHabit.isActive()
        );
    }

    public List<HabitResponse> findAll(String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return habitRepository.findByUser(user)
            .stream()
            .map(habit -> new HabitResponse(
                habit.getId(),
                habit.getTitle(),
                habit.getDescription(),
                habit.isActive()
            ))
            .toList();
    }
}
