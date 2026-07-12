package com.mindvault.habit.service;

import com.mindvault.habit.dto.CreateHabitRequest;
import com.mindvault.habit.dto.HabitResponse;
import com.mindvault.habit.dto.UpdateHabitRequest;
import com.mindvault.habit.entity.Habit;
import com.mindvault.habit.repository.HabitRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

        return toResponse(savedHabit);
    }

    public List<HabitResponse> findAll(String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return habitRepository.findByUser(user)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    public HabitResponse update(UUID id, UpdateHabitRequest request, String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Habit habit = habitRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You cannot update another user's habit");
        }

        habit.setTitle(request.title());
        habit.setDescription(request.description());
        habit.setActive(request.active());

        Habit updatedHabit = habitRepository.save(habit);

        return toResponse(updatedHabit);
    }

    public void delete(UUID id, String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Habit habit = habitRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        if (!habit.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You cannot delete another user's habit");
        }

        habitRepository.delete(habit);
    }

    private HabitResponse toResponse(Habit habit) {
        return new HabitResponse(
            habit.getId(),
            habit.getTitle(),
            habit.getDescription(),
            habit.isActive()
        );
    }
}
