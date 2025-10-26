package com.example.jpay.repository;

import com.example.jpay.entity.Transaction;
import com.example.jpay.entity.Account;
import com.example.jpay.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface TransactionRepository extends JpaRepository <Transaction, String> {
    List<Transaction> findByToAccount(Account toAccount);
    List<Transaction> findByFromAccount(Account fromAccount);
    List<Transaction> findByType(TransactionType type);

    //Custom Query
    @Query("SELECT t from Transaction t WHERE t.createdAt BETWEEN :start AND :end ")
    List<Transaction> findByDateRange(LocalDateTime start, LocalDateTime end);
}
