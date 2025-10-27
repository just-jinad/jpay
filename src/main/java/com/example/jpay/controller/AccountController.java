package com.example.jpay.controller;

import com.example.jpay.entity.Account;
import com.example.jpay.entity.Customer;

import com.example.jpay.repository.AccountRepository;
import com.example.jpay.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        
        Optional<Customer> customer = customerRepository.findById(account.getCustomer().getCustomerId());

        if(customer.isEmpty()){
           return ResponseEntity.badRequest().build();
        }
        account.setCustomer(customer.get());
        Account savedAccount = accountRepository.save(account);

        return  ResponseEntity.ok(savedAccount);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountByCustomer (@PathVariable String customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<Account> accounts = accountRepository.findByCustomer(customer.get());
        return ResponseEntity.ok(accounts);
    }
}


