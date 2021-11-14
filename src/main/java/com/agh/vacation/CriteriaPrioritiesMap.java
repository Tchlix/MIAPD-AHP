package com.agh.vacation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Filip Piwosz
 */
class CriteriaPrioritiesMap {
    private Map<Criterion, Double> map;

    CriteriaPrioritiesMap(Map<Criterion, Double> map) {
        this.map = map;
    }

    Set<Entry<Criterion, Double>> entrySet() {
        return this.map.entrySet();
    }
}
