package com.mindvault.finance.repository;

import com.mindvault.finance.entity.Transaction;
import com.mindvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByUserOrderByCreatedAtDesc(User user);

}
