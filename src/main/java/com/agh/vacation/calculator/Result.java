package com.agh.vacation.calculator;

import com.agh.vacation.something.VacationDestination;
import org.sk.PrettyTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.System.lineSeparator;
import static java.util.Map.Entry.comparingByValue;

public class Result {
    private final Map<VacationDestination, Double> map;

    Result(Map<VacationDestination, Double> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        PrettyTable table = new PrettyTable("City","Profit");
        List<Entry<VacationDestination, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(comparingByValue(Collections.reverseOrder()));
        list.forEach(entry -> table.addRow(entry.getKey().name,
                        entry.getValue().toString()));
        return table.toString();
        /*StringBuilder builder = new StringBuilder();
        List<Entry<VacationDestination, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(comparingByValue(Collections.reverseOrder()));
        list.forEach(entry -> builder.append(entry.getKey().name)
                .append(" = ")
                .append(entry.getValue())
                .append(lineSeparator()));
        return builder.toString();*/
    }

}
