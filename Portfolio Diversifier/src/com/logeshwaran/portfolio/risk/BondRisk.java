package com.logeshwaran.portfolio.risk;

import java.math.BigDecimal;

public class BondRisk implements RiskStrategy {

    @Override
    public BigDecimal calculateRisk(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(0.1));
    }
}