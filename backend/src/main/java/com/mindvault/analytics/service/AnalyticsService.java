package com.mindvault.analytics.service;

import com.mindvault.analytics.dto.AnalyticsResponse;
import com.mindvault.analytics.dto.DashboardResponse;
import com.mindvault.analytics.dto.HabitAnalyticsResponse;
import com.mindvault.finance.repository.TransactionRepository;
import com.mindvault.analytics.dto.GoalAnalyticsResponse;
import com.mindvault.goal.entity.Goal;
import com.mindvault.analytics.dto.ProjectAnalyticsResponse;
import com.mindvault.project.entity.Project;
import com.mindvault.project.entity.ProjectStatus;
import com.mindvault.goal.repository.GoalRepository;
import com.mindvault.habit.repository.HabitRepository;
import com.mindvault.habitrecord.repository.HabitRecordRepository;
import com.mindvault.note.repository.NoteRepository;
import com.mindvault.project.repository.ProjectRepository;
import com.mindvault.user.entity.User;
import com.mindvault.habit.entity.Habit;
import com.mindvault.analytics.dto.FinanceAnalyticsResponse;
import com.mindvault.finance.entity.Transaction;
import com.mindvault.finance.entity.TransactionType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
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

    private final HabitRecordRepository habitRecordRepository;

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

    public HabitAnalyticsResponse habits(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        List<Habit> habits = habitRepository.findByUser(user);

        long totalHabits = habits.size();

        long activeHabits = habits.stream()
            .filter(Habit::isActive)
            .count();

        long completedToday =
            habitRecordRepository.countByHabitInAndDateAndCompletedTrue(
                habits,
                LocalDate.now()
            );

        BigDecimal completionRate = BigDecimal.ZERO;

        if (activeHabits > 0) {

            completionRate = BigDecimal.valueOf(completedToday)
                .multiply(BigDecimal.valueOf(100))
                .divide(
                    BigDecimal.valueOf(activeHabits),
                    2,
                    RoundingMode.HALF_UP
                );

        }

        return new HabitAnalyticsResponse(

            totalHabits,

            activeHabits,

            completedToday,

            completionRate

        );

    }

    public FinanceAnalyticsResponse finance(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        List<Transaction> transactions =
            transactionRepository.findAllByUser(user);

        BigDecimal totalIncome = transactions.stream()
            .filter(t -> t.getType() == TransactionType.INCOME)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
            .filter(t -> t.getType() == TransactionType.EXPENSE)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);

        long incomeTransactions =
            transactionRepository.countByUserAndType(
                user,
                TransactionType.INCOME
            );

        long expenseTransactions =
            transactionRepository.countByUserAndType(
                user,
                TransactionType.EXPENSE
            );

        return new FinanceAnalyticsResponse(

            balance,

            totalIncome,

            totalExpense,

            incomeTransactions,

            expenseTransactions

        );

    }

    public AnalyticsResponse analytics(
        String email
    ) {

        return new AnalyticsResponse(

            dashboard(email),

            habits(email),

            finance(email)

        );

    }

    public GoalAnalyticsResponse goals(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        List<Goal> goals = goalRepository.findAllByUser(user);

        long totalGoals = goals.size();

        long completedGoals = goals.stream()
            .filter(Goal::isCompleted)
            .count();

        long pendingGoals = totalGoals - completedGoals;

        BigDecimal completionRate = BigDecimal.ZERO;

        if (totalGoals > 0) {

            completionRate = BigDecimal.valueOf(completedGoals)
                .multiply(BigDecimal.valueOf(100))
                .divide(
                    BigDecimal.valueOf(totalGoals),
                    2,
                    RoundingMode.HALF_UP
                );

        }

        return new GoalAnalyticsResponse(

            totalGoals,

            completedGoals,

            pendingGoals,

            completionRate

        );

    }

    public ProjectAnalyticsResponse projects(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        List<Project> projects = projectRepository.findAllByUser(user);

        long totalProjects = projects.size();

        long completedProjects = projects.stream()
            .filter(project -> project.getStatus() == ProjectStatus.DONE)
            .count();

        long inProgressProjects = projects.stream()
            .filter(project -> project.getStatus() == ProjectStatus.IN_PROGRESS)
            .count();

        long todoProjects = projects.stream()
            .filter(project -> project.getStatus() == ProjectStatus.TODO)
            .count();

        long onHoldProjects = projects.stream()
            .filter(project -> project.getStatus() == ProjectStatus.ON_HOLD)
            .count();

        BigDecimal completionRate = BigDecimal.ZERO;

        if (totalProjects > 0) {

            completionRate = BigDecimal.valueOf(completedProjects)
                .multiply(BigDecimal.valueOf(100))
                .divide(
                    BigDecimal.valueOf(totalProjects),
                    2,
                    RoundingMode.HALF_UP
                );

        }

        return new ProjectAnalyticsResponse(

            totalProjects,

            completedProjects,

            inProgressProjects,

            todoProjects,

            onHoldProjects,

            completionRate

        );

    }

}
