package com.mindvault.finance.service;

import com.mindvault.finance.dto.CreateTransactionRequest;
import com.mindvault.finance.dto.TransactionResponse;
import com.mindvault.finance.entity.Transaction;
import com.mindvault.finance.repository.TransactionRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
