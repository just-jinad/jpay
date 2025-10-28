package com.example.jpay.repository;

import com.example.jpay.entity.Account;
import com.example.jpay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpay.entity.enums.AccountType;
import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository <Account, String> {

    List<Account> findByCustomer(Customer customer);
    List<Account> findByAccountType(AccountType accountType);
    Optional<Account> findByAccountNumber(Account accountNumber);
    
}
