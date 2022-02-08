package com.agh.vacation.something;

import java.util.List;

import static java.lang.System.lineSeparator;

/**
 * @author Filip Piwosz
 */
public class InconsistencyResult {
    public final List<Double> saatyResult;
    public final List<Double> crResult;
    public final List<Double> koczkodajResult;
    public final List<Criterion> criteria;

    public InconsistencyResult(List<Double> saatyResult, List<Double> crResult, List<Double> koczkodajResult, List<Criterion> criteria) {
        this.saatyResult = saatyResult;
        this.crResult = crResult;
        this.koczkodajResult = koczkodajResult;
        this.criteria = criteria;
    }

    public String display() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("\t")
                .append("Saaty")
                .append("  CR")
                .append("  Koczkodaj")
                .append(lineSeparator());
        for (int i = 0; i < criteria.size(); i++) {
            builder
                    .append(criteria.get(i))
                    .append("  ")
                    .append(saatyResult.get(i))
                    .append("  ")
                    .append(crResult.get(i))
                    .append("  ")
                    .append(koczkodajResult.get(i))
                    .append(lineSeparator());
        }
        return builder.toString();

    }

}
