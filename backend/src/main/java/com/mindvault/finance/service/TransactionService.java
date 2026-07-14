package com.mindvault.finance.service;

import com.mindvault.finance.dto.*;
import com.mindvault.finance.entity.Transaction;
import com.mindvault.finance.repository.TransactionRepository;
import com.mindvault.finance.dto.TransactionSummaryResponse;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mindvault.finance.entity.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionResponse create(
        CreateTransactionRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Transaction transaction = new Transaction();

        transaction.setTitle(request.title());
        transaction.setDescription(request.description());
        transaction.setAmount(request.amount());
        transaction.setType(request.type());
        transaction.setCategory(request.category());
        transaction.setTransactionDate(request.transactionDate());
        transaction.setUser(user);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return map(savedTransaction);

    }

    private TransactionResponse map(
        Transaction transaction
    ) {

        return new TransactionResponse(

            transaction.getId(),

            transaction.getTitle(),

            transaction.getDescription(),

            transaction.getAmount(),

            transaction.getType(),

            transaction.getCategory(),

            transaction.getTransactionDate()

        );

    }

    public List<TransactionResponse> findAll(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return transactionRepository
            .findAllByUserOrderByCreatedAtDesc(user)
            .stream()
            .map(this::map)
            .toList();

    }

    public TransactionResponse update(
        UUID id,
        CreateTransactionRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Transaction not found"));

        validateOwnership(transaction, user);

        transaction.setTitle(request.title());
        transaction.setDescription(request.description());
        transaction.setAmount(request.amount());
        transaction.setType(request.type());
        transaction.setCategory(request.category());
        transaction.setTransactionDate(request.transactionDate());

        Transaction updatedTransaction = transactionRepository.save(transaction);

        return map(updatedTransaction);

    }

    private void validateOwnership(
        Transaction transaction,
        User user
    ) {

        if (!transaction.getUser().getId().equals(user.getId())) {

            throw new IllegalArgumentException(
                "Transaction does not belong to user"
            );

        }

    }

    public void delete(
        UUID id,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() ->
                new IllegalArgumentException("Transaction not found"));

        validateOwnership(transaction, user);

        transactionRepository.delete(transaction);

    }

    public TransactionDashboardResponse dashboard(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        List<Transaction> transactions =
            transactionRepository.findAllByUserOrderByCreatedAtDesc(user);

        BigDecimal totalIncome = transactions.stream()

            .filter(t -> t.getType() == TransactionType.INCOME)

            .map(Transaction::getAmount)

            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()

            .filter(t -> t.getType() == TransactionType.EXPENSE)

            .map(Transaction::getAmount)

            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpense);

        long incomeCount = transactions.stream()

            .filter(t -> t.getType() == TransactionType.INCOME)

            .count();

        long expenseCount = transactions.stream()

            .filter(t -> t.getType() == TransactionType.EXPENSE)

            .count();

        return new TransactionDashboardResponse(

            totalIncome,

            totalExpense,

            balance,

            incomeCount,

            expenseCount

        );

    }

    public List<TransactionResponse> findByPeriod(

        LocalDate startDate,

        LocalDate endDate,

        String email

    ) {

        User user = userRepository.findByEmail(email)

            .orElseThrow(() ->

                new IllegalArgumentException("User not found"));

        return transactionRepository

            .findByUserAndTransactionDateBetweenOrderByTransactionDateDesc(

                user,

                startDate,

                endDate

            )

            .stream()

            .map(this::map)

            .toList();

    }

    public TransactionBalanceResponse balance(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        List<Transaction> transactions =
            transactionRepository.findAllByUserOrderByCreatedAtDesc(user);

        BigDecimal totalIncome = transactions.stream()
            .filter(t -> t.getType() == TransactionType.INCOME)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = transactions.stream()
            .filter(t -> t.getType() == TransactionType.EXPENSE)
            .map(Transaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TransactionBalanceResponse(
            totalIncome.subtract(totalExpense)
        );

    }

    public TransactionSummaryResponse summary(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        List<Transaction> transactions =
            transactionRepository.findAllByUserOrderByCreatedAtDesc(user);

        BigDecimal largestIncome = transactions.stream()

            .filter(t -> t.getType() == TransactionType.INCOME)

            .map(Transaction::getAmount)

            .max(BigDecimal::compareTo)

            .orElse(BigDecimal.ZERO);

        BigDecimal largestExpense = transactions.stream()

            .filter(t -> t.getType() == TransactionType.EXPENSE)

            .map(Transaction::getAmount)

            .max(BigDecimal::compareTo)

            .orElse(BigDecimal.ZERO);

        BigDecimal averageIncome = transactions.stream()

            .filter(t -> t.getType() == TransactionType.INCOME)

            .map(Transaction::getAmount)

            .reduce(BigDecimal.ZERO, BigDecimal::add);

        long incomeCount = transactions.stream()

            .filter(t -> t.getType() == TransactionType.INCOME)

            .count();

        if (incomeCount > 0) {
            averageIncome = averageIncome.divide(
                BigDecimal.valueOf(incomeCount),
                2,
                java.math.RoundingMode.HALF_UP
            );
        }

        BigDecimal averageExpense = transactions.stream()

            .filter(t -> t.getType() == TransactionType.EXPENSE)

            .map(Transaction::getAmount)

            .reduce(BigDecimal.ZERO, BigDecimal::add);

        long expenseCount = transactions.stream()

            .filter(t -> t.getType() == TransactionType.EXPENSE)

            .count();

        if (expenseCount > 0) {
            averageExpense = averageExpense.divide(
                BigDecimal.valueOf(expenseCount),
                2,
                java.math.RoundingMode.HALF_UP
            );
        }

        return new TransactionSummaryResponse(

            transactions.size(),

            largestIncome,

            largestExpense,

            averageIncome,

            averageExpense

        );

    }

}
