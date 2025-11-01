package com.example.jpay.service;

import org.springframework.stereotype.Service;

import com.example.jpay.entity.Account;
import com.example.jpay.entity.Transaction;
import com.example.jpay.entity.enums.TransactionType;
import com.example.jpay.repository.TransactionRepository;
import com.example.jpay.repository.AccountRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

   
}
