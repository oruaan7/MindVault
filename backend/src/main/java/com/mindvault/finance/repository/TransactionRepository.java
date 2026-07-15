package com.mindvault.finance.repository;

import com.mindvault.finance.entity.Transaction;
import com.mindvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mindvault.finance.entity.TransactionType;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByUser(User user);
    List<Transaction> findAllByUserOrderByCreatedAtDesc(User user);
    List<Transaction> findByUserAndTransactionDateBetweenOrderByTransactionDateDesc(
        User user,
        LocalDate startDate,
        LocalDate endDate
    );

    long countByUser(User user);
    long countByUserAndType(
        User user,
        TransactionType type
    );
}
