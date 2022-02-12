package com.agh.vacation.calculator;

import com.agh.vacation.something.Criterion;
import org.sk.PrettyTable;

import java.util.List;

/**
 * @author Filip Piwosz
 */
public record InconsistencyResult(List<Double> saatyResult, List<Double> crResult,
                                  List<Double> koczkodajResult, List<Criterion> criteria) {

    @Override
    public String toString() {
        PrettyTable table = new PrettyTable("Name", "Saaty", "CR", "Koczkodaj");
        for (int i = 0; i < criteria.size(); i++)
            table.addRow(criteria.get(i).toString(), saatyResult.get(i).toString(),
                    crResult.get(i).toString(), koczkodajResult.get(i).toString());
        return table.toString();
        /*
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
*/

    }

}
