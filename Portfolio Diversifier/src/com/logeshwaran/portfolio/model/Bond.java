package com.logeshwaran.portfolio.model;

import com.logeshwaran.portfolio.risk.BondRisk;

import java.math.BigDecimal;

public class Bond extends Asset {

    public Bond(String name, BigDecimal value) {
        super(name, value, new BondRisk());
    }
}