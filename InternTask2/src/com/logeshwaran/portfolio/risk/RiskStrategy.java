package com.logeshwaran.portfolio.risk;

import java.math.BigDecimal;

public interface RiskStrategy {
    BigDecimal calculateRisk(BigDecimal value);
}