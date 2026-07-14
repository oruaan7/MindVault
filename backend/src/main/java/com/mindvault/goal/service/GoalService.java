package com.mindvault.goal.service;

import com.mindvault.goal.dto.CreateGoalRequest;
import com.mindvault.goal.dto.GoalResponse;
import com.mindvault.goal.dto.UpdateGoalRequest;
import com.mindvault.goal.entity.Goal;
import com.mindvault.goal.repository.GoalRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalResponse create(CreateGoalRequest request, String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Goal goal = new Goal();

        goal.setTitle(request.title());
        goal.setDescription(request.description());
        goal.setTargetValue(request.targetValue());
        goal.setCurrentValue(0);
        goal.setCompleted(false);
        goal.setUser(user);

        Goal savedGoal = goalRepository.save(goal);

        return map(savedGoal);
    }

    public List<GoalResponse> findAll(String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return goalRepository.findAllByUserOrderByCreatedAtAsc(user)
            .stream()
            .map(this::map)
            .toList();
    }

    public GoalResponse update(
        UUID id,
        UpdateGoalRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Goal goal = goalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Goal not found"));

        if (!goal.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Goal does not belong to user");
        }

        goal.setTitle(request.title());
        goal.setDescription(request.description());
        goal.setTargetValue(request.targetValue());

        Goal updatedGoal = goalRepository.save(goal);

        return map(updatedGoal);
    }

    private GoalResponse map(Goal goal) {

        return new GoalResponse(
            goal.getId(),
            goal.getTitle(),
            goal.getDescription(),
            goal.getTargetValue(),
            goal.getCurrentValue(),
            goal.isCompleted()
        );
    }

}
