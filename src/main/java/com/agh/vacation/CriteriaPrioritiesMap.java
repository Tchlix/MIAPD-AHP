package com.agh.vacation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Wrapper for map of Criteria and Priority represented by Double
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
}
