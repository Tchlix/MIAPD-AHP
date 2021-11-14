package com.agh.vacation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.System.lineSeparator;
import static java.util.Collections.reverse;
import static java.util.Map.Entry.comparingByValue;

/**
 * @author Filip Piwosz
 */
class Result {
    private Map<VacationDestination, Double> map;

    public Result(Map<VacationDestination, Double> map) {
        this.map = map;
    }

    public String display() {
        StringBuilder builder = new StringBuilder();
        List<Entry<VacationDestination, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(comparingByValue(Collections.reverseOrder()));
        list.forEach(entry -> {
            builder.append(entry);
            builder.append(lineSeparator());
        });
        return builder.toString();
    }

}
