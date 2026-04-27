package service;

import enums.TransactionType;
import exception.CurrencyNotFoundException;
import exception.InsufficientBalanceException;
import model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class CurrencyVault {

    private static final int MAX_HISTORY = 100;

    private final Map<String, BigDecimal> vault = new HashMap<>();
    private final LinkedList<Transaction> transactions = new LinkedList<>();
    private int counter = 1;

    private String normalize(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid currency");
        }
        return currency.trim().toUpperCase();
    }

    private void recordTransaction(TransactionType type, String currency, BigDecimal amount) {

        if (transactions.size() == MAX_HISTORY) {
            transactions.removeFirst();
        }

        transactions.add(new Transaction(
                "TXN-" + counter++,
                type,
                currency,
                amount,
                LocalDateTime.now()
        ));
    }

    public void addCurrency(String currency, BigDecimal initialBalance) {

        currency = normalize(currency);

        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid balance");
        }

        if (vault.containsKey(currency)) {
            throw new IllegalArgumentException("Currency already exists");
        }

        vault.put(currency, initialBalance);
        recordTransaction(TransactionType.ADD, currency, initialBalance);
    }

    public void deposit(String currency, BigDecimal amount) {

        currency = normalize(currency);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        BigDecimal balance = vault.get(currency);

        if (balance == null) {
            throw new CurrencyNotFoundException("Currency not found");
        }

        vault.put(currency, balance.add(amount));
        recordTransaction(TransactionType.DEPOSIT, currency, amount);
    }

    public void withdraw(String currency, BigDecimal amount) {

        currency = normalize(currency);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        BigDecimal balance = vault.get(currency);

        if (balance == null) {
            throw new CurrencyNotFoundException("Currency not found");
        }

        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        vault.put(currency, balance.subtract(amount));
        recordTransaction(TransactionType.WITHDRAW, currency, amount);
    }

    public Map<String, BigDecimal> getAllBalances() {
        return Collections.unmodifiableMap(vault);
    }

    public List<String> getZeroBalanceCurrencies() {

        List<String> result = new ArrayList<>();

        for (var entry : vault.entrySet()) {
            if (entry.getValue().compareTo(BigDecimal.ZERO) == 0) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public List<Transaction> getLastTransactions(int count) {

        if (count <= 0) {
            throw new IllegalArgumentException("Invalid count");
        }

        int size = transactions.size();
        int start = Math.max(0, size - count);

        return new ArrayList<>(transactions.subList(start, size)); // safe copy
    }
}