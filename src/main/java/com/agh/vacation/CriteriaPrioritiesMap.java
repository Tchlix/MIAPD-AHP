package com.agh.vacation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 */
class CriteriaPrioritiesMap {
    private final Map<Criterion, Double> map;

    CriteriaPrioritiesMap(Map<Criterion, Double> map) {
        this.map = map;
    }

    Set<Entry<Criterion, Double>> entrySet() {
        return this.map.entrySet();
    }
}
