package com.mindvault.analytics.service;

import com.mindvault.analytics.dto.DashboardResponse;
import com.mindvault.finance.repository.TransactionRepository;
import com.mindvault.goal.repository.GoalRepository;
import com.mindvault.habit.repository.HabitRepository;
import com.mindvault.habitrecord.repository.HabitRecordRepository;
import com.mindvault.note.repository.NoteRepository;
import com.mindvault.project.repository.ProjectRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HabitRepository habitRepository;

    @Mock
    private GoalRepository goalRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private HabitRecordRepository habitRecordRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AnalyticsService analyticsService;

    @Test
    void shouldReturnDashboard() {

        User user = new User();

        when(userRepository.findByEmail("user@email.com"))
            .thenReturn(Optional.of(user));

        when(habitRepository.countByUser(user)).thenReturn(5L);
        when(goalRepository.countByUser(user)).thenReturn(3L);
        when(projectRepository.countByUser(user)).thenReturn(2L);
        when(noteRepository.countByUser(user)).thenReturn(10L);
        when(transactionRepository.countByUser(user)).thenReturn(8L);

        DashboardResponse response =
            analyticsService.dashboard("user@email.com");

        assertAll(
            () -> assertEquals(5L, response.totalHabits()),
            () -> assertEquals(3L, response.totalGoals()),
            () -> assertEquals(2L, response.totalProjects()),
            () -> assertEquals(10L, response.totalNotes()),
            () -> assertEquals(8L, response.totalTransactions())
        );

        verify(userRepository).findByEmail("user@email.com");
        verify(habitRepository).countByUser(user);
        verify(goalRepository).countByUser(user);
        verify(projectRepository).countByUser(user);
        verify(noteRepository).countByUser(user);
        verify(transactionRepository).countByUser(user);

    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {

        when(userRepository.findByEmail("invalid@email.com"))
            .thenReturn(Optional.empty());

        IllegalArgumentException exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> analyticsService.dashboard("invalid@email.com")
            );

        assertEquals("User not found", exception.getMessage());

        verify(userRepository).findByEmail("invalid@email.com");

    }

}
