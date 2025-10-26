package com.example.jpay.controller;

import com.example.jpay.entity.Customer;
import com.example.jpay.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerRepository CustomerRepository;

    //Save Customer
    @PostMapping
    public ResponseEntity <Customer> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = CustomerRepository.save(customer);

        return ResponseEntity.ok(savedCustomer);
    }
    
    // Get saved customers
    @GetMapping
    public ResponseEntity <List<Customer>> getAllCustomers(){

        List<Customer> customers  = CustomerRepository.findAll();

        return ResponseEntity.ok(customers);
    }

    // Get Customer by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){
        Optional<Customer> customer = CustomerRepository.findByEmail(email);

        return customer.map(ResponseEntity::ok)
        .orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    
}
