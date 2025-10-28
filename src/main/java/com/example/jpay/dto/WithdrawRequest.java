package com.example.jpay.dto;

import java.math.BigDecimal;

public record WithdrawRequest (String accountId, BigDecimal amount) {
    
}
