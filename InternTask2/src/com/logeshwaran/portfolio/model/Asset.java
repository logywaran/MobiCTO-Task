package com.logeshwaran.portfolio.model;

import com.logeshwaran.portfolio.risk.RiskStrategy;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Asset {

    protected String name;
    protected BigDecimal value;
    protected RiskStrategy riskStrategy;

    public Asset(String name, BigDecimal value, RiskStrategy riskStrategy) {
        this.name = name;
        this.value = value;
        this.riskStrategy = riskStrategy;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal calculateRisk() {
        return riskStrategy.calculateRisk(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Asset)) return false;

        Asset other = (Asset) obj;
        return Objects.equals(this.name, other.name)
                && this.getClass().equals(other.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getClass());
    }
}