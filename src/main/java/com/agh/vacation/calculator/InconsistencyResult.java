package com.agh.vacation.calculator;

import com.agh.vacation.ds.Criterion;
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
    }

}
