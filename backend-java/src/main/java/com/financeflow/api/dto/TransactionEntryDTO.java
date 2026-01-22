package com.financeflow.api.dto;

import java.math.BigDecimal;

public record TransactionEntryDTO(String date, BigDecimal amount, String type) {}
