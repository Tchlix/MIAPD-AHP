package com.agh.vacation.something;

import java.util.List;

import static java.lang.System.lineSeparator;

/**
 * @author Filip Piwosz
 */
public record InconsistencyResult(List<Double> saatyResult, List<Double> crResult,
                                  List<Double> koczkodajResult, List<Criterion> criteria) {

    @Override
    public String toString() {
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
