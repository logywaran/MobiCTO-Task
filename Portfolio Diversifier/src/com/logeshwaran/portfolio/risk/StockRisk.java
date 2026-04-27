package com.logeshwaran.portfolio.risk;

import java.math.BigDecimal;

public class StockRisk implements RiskStrategy {

    @Override
    public BigDecimal calculateRisk(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(0.2));
    }
}