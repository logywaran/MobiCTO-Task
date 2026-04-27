package com.logeshwaran.portfolio.model;

import com.logeshwaran.portfolio.risk.StockRisk;

import java.math.BigDecimal;

public class Stock extends Asset {

    public Stock(String name, BigDecimal value) {
        super(name, value, new StockRisk());
    }
}