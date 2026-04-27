package model;

import enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final String id;
    private final TransactionType type;
    private final String currency;
    private final BigDecimal amount;
    private final LocalDateTime time;

    public Transaction(String id, TransactionType type, String currency, BigDecimal amount, LocalDateTime time) {
        this.id = id;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s",
                id, type, currency, amount, time);
    }
}