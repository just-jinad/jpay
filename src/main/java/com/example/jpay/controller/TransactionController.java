package com.example.jpay.controller;

import com.example.jpay.entity.Account;
import com.example.jpay.entity.Transaction;
import com.example.jpay.entity.enums.TransactionType;
import com.example.jpay.repository.TransactionRepository;
import com.example.jpay.repository.AccountRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// dto
import com.example.jpay.dto.DepositRequest;
import com.example.jpay.dto.TransferRequest;
import com.example.jpay.dto.WithdrawRequest;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody DepositRequest request){

        Optional<Account> accountOpt = accountRepository.findById(request.accountId());
        if(accountOpt.isEmpty()){
           return ResponseEntity.badRequest().body(null);
        }

        Account account = accountOpt.get();
        BigDecimal currentBalance = new BigDecimal(account.getBalance());
        BigDecimal newBalance = currentBalance.add(request.amount());
        account.setBalance(newBalance.toString());
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setFromAccount(account);
        tx.setType(TransactionType.DEPOSIT);
        tx.setAmount(request.amount());
        tx.setCreatedAt(LocalDateTime.now());
        Transaction saved = transactionRepository.save(tx);

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawRequest request){
        Optional<Account> accountOpt = accountRepository.findById(request.accountId());
        if (accountOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Account account = accountOpt.get();
        BigDecimal currentBalance = new BigDecimal(account.getBalance());
       
        if (currentBalance.compareTo(request.amount()) < 0) {
            return ResponseEntity.badRequest().body(null);
        }

        BigDecimal newBalance = currentBalance.subtract(request.amount());
        account.setBalance(newBalance.toString());
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setFromAccount(account); 
        tx.setAmount(newBalance);
        tx.setType(TransactionType.WITHDRAWAL);
        tx.setCreatedAt(LocalDateTime.now());

       Transaction saved =  transactionRepository.save(tx);
       return ResponseEntity.ok(saved);
    }
}
