package com.example.jpay.controller;


import java.util.Map;

// import org.hibernate.mapping.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public Map<String, String> hello(){
        return Map.of("message", "Welcome to Jpay");
    }
    
}
