package com.mindvault.goal.service;

import com.mindvault.goal.dto.CreateGoalRequest;
import com.mindvault.goal.dto.GoalResponse;
import com.mindvault.goal.entity.Goal;
import com.mindvault.goal.repository.GoalRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalResponse create(
        CreateGoalRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Goal goal = new Goal();

        goal.setTitle(request.title());
        goal.setDescription(request.description());
        goal.setTargetValue(request.targetValue());
        goal.setCurrentValue(0);
        goal.setCompleted(false);
        goal.setUser(user);

        Goal savedGoal = goalRepository.save(goal);

        return new GoalResponse(

            savedGoal.getId(),

            savedGoal.getTitle(),

            savedGoal.getDescription(),

            savedGoal.getTargetValue(),

            savedGoal.getCurrentValue(),

            savedGoal.isCompleted()

        );

    }

}
