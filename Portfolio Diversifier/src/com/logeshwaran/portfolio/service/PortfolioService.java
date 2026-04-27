package com.logeshwaran.portfolio.service;

import com.logeshwaran.portfolio.model.Asset;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class PortfolioService {

    private final Set<Asset> portfolio = new HashSet<>();

    public boolean addAsset(Asset asset) {
        return portfolio.add(asset);
    }

    public BigDecimal getTotalValue() {

        BigDecimal total = BigDecimal.ZERO;

        for (Asset a : portfolio) {
            total = total.add(a.getValue());
        }

        return total;
    }

    public BigDecimal getTotalRisk() {

        BigDecimal total = BigDecimal.ZERO;

        for (Asset a : portfolio) {
            total = total.add(a.calculateRisk());
        }

        return total;
    }

    public Set<Asset> getAssets() {
        return portfolio;
    }

    public int size() {
        return portfolio.size();
    }
}