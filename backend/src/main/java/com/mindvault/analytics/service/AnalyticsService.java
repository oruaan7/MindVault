package com.mindvault.analytics.service;

import com.mindvault.analytics.dto.DashboardResponse;
import com.mindvault.analytics.dto.HabitAnalyticsResponse;
import com.mindvault.finance.repository.TransactionRepository;
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

}
