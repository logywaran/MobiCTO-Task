package app;

import service.CurrencyVault;
import model.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CurrencyVault vault = new CurrencyVault();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== CURRENCY VAULT MENU =====");
            System.out.println("1. Add Currency");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show All Balances");
            System.out.println("5. Show Zero Balances");
            System.out.println("6. Show Transactions");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1 -> {
                        System.out.print("Enter currency: ");
                        String c = sc.next();
                        System.out.print("Enter initial balance: ");
                        BigDecimal b = sc.nextBigDecimal();
                        vault.addCurrency(c, b);
                        System.out.println("Currency added");
                    }

                    case 2 -> {
                        System.out.print("Enter currency: ");
                        String c = sc.next();
                        System.out.print("Enter amount: ");
                        BigDecimal a = sc.nextBigDecimal();
                        vault.deposit(c, a);
                        System.out.println("Deposited");
                    }

                    case 3 -> {
                        System.out.print("Enter currency: ");
                        String c = sc.next();
                        System.out.print("Enter amount: ");
                        BigDecimal a = sc.nextBigDecimal();
                        vault.withdraw(c, a);
                        System.out.println("Withdrawn");
                    }

                    case 4 -> {
                        System.out.println("\n===== BALANCES =====");
                        vault.getAllBalances()
                                .forEach((k, v) -> System.out.println(k + " -> " + v));
                    }

                    case 5 -> {
                        System.out.println("\n===== ZERO BALANCES =====");
                        List<String> zeros = vault.getZeroBalanceCurrencies();

                        if (zeros.isEmpty()) {
                            System.out.println("None");
                        } else {
                            zeros.forEach(System.out::println);
                        }
                    }

                    case 6 -> {
                        System.out.println("\nChoose option:");
                        System.out.println("1. Last 10");
                        System.out.println("2. Last 30");
                        System.out.println("3. Custom");
                        System.out.print("Enter choice: ");

                        int opt = sc.nextInt();
                        int count;

                        switch (opt) {
                            case 1 -> count = 10;
                            case 2 -> count = 30;
                            case 3 -> {
                                System.out.print("Enter number of transactions: ");
                                count = sc.nextInt();
                            }
                            default -> {
                                System.out.println("Invalid option");
                                continue;
                            }
                        }

                        List<Transaction> txns = vault.getLastTransactions(count);

                        if (txns.isEmpty()) {
                            System.out.println("No transactions found");
                        } else {
                            txns.forEach(System.out::println);
                        }
                    }

                    case 7 -> {
                        System.out.println("Exiting...");
                        return;
                    }

                    default -> System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}