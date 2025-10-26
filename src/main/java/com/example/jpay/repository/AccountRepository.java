package com.example.jpay.repository;

import com.example.jpay.entity.Account;
import com.example.jpay.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpay.entity.enums.AccountType;
import java.util.List;


public interface AccountRepository extends JpaRepository <Account, String> {

    List<Account> findByCustomer(Customer customer);
    List<Account> findByAccountType(AccountType accountType);
}
