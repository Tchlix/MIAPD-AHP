package com.agh.vacation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 */
public class CriteriaPrioritiesMap {
    private final Map<Criterion, Double> map;

    public CriteriaPrioritiesMap(Map<Criterion, Double> map) {
        this.map = map;
    }

    Set<Entry<Criterion, Double>> entrySet() {
        return this.map.entrySet();
    }

    Set<Criterion> keySet() {
        return this.map.keySet();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Entry<Criterion, Double> entry : map.entrySet()) {
            builder.append(entry);
            builder.append(System.lineSeparator());
        }
        if (map.size() != 0) {
            builder.delete(builder.length() - 1, builder.length());
        }
        return builder.toString();
    }
}
