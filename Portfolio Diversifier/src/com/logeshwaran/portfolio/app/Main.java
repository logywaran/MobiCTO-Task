package com.logeshwaran.portfolio.app;

import com.logeshwaran.portfolio.model.*;
import com.logeshwaran.portfolio.service.PortfolioService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PortfolioService service = new PortfolioService();

        while (true) {

            System.out.println("\n===== PORTFOLIO MENU =====");
            System.out.println("1. Add Asset");
            System.out.println("2. View Summary");
            System.out.println("3. View Assets");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.println("1=Stock 2=Bond");
                    int type = sc.nextInt();

                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Value: ");
                    BigDecimal value = sc.nextBigDecimal();

                    Asset asset;

                    if (type == 1) {
                        asset = new Stock(name, value);
                    } else {
                        asset = new Bond(name, value);
                    }

                    if (service.addAsset(asset)) {
                        System.out.println("Added");
                    } else {
                        System.out.println("Duplicate asset");
                    }
                }

                case 2 -> {
                    System.out.println("TOTAL VALUE: " + service.getTotalValue());
                    System.out.println("TOTAL RISK: " + service.getTotalRisk());
                    System.out.println("COUNT: " + service.size());
                }

                case 3 -> {
                    service.getAssets().forEach(a ->
                            System.out.println(
                                    a.getClass().getSimpleName()
                                            + " | " + a.getName()
                                            + " | " + a.getValue()
                            )
                    );
                }

                case 4 -> {
                    System.out.println("Exit");
                    return;
                }
            }
        }
    }
}