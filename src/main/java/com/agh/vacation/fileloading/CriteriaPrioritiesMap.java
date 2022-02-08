package com.agh.vacation.fileloading;

import com.agh.vacation.something.Criterion;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static java.lang.System.lineSeparator;

/**
 * Wrapper for map of Criteria and Priority represented by Double
 */
public class CriteriaPrioritiesMap {
    private final Map<Criterion, Double> map;

    public CriteriaPrioritiesMap(Map<Criterion, Double> map) {
        this.map = map;
    }

    public Set<Entry<Criterion, Double>> entrySet() {
        return this.map.entrySet();
    }

    public Set<Criterion> keySet() {
        return this.map.keySet();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Criterion : Priority value" + lineSeparator());
        map.forEach((key, value) ->
                builder.append(key)
                .append(" : ")
                .append(value)
                .append(lineSeparator()));
        if (!map.isEmpty()) {
            builder.delete(builder.length() - 1, builder.length());
        }
        return builder.toString();
    }
}
