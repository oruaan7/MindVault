package com.mindvault.analytics.service;

import com.mindvault.analytics.dto.DashboardResponse;
import com.mindvault.finance.repository.TransactionRepository;
import com.mindvault.goal.repository.GoalRepository;
import com.mindvault.habit.repository.HabitRepository;
import com.mindvault.note.repository.NoteRepository;
import com.mindvault.project.repository.ProjectRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final UserRepository userRepository;

    private final HabitRepository habitRepository;

    private final GoalRepository goalRepository;

    private final ProjectRepository projectRepository;

    private final NoteRepository noteRepository;

    private final TransactionRepository transactionRepository;

    public DashboardResponse dashboard(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return new DashboardResponse(

            habitRepository.countByUser(user),

            goalRepository.countByUser(user),

            projectRepository.countByUser(user),

            noteRepository.countByUser(user),

            transactionRepository.countByUser(user)

        );

    }

}
