package com.agh.vacation;

import java.util.Map;
import java.util.Set;

/**
 * @author Filip Piwosz
 */
class IndexMap {
    private final Map<PairwiseComparableObject, Integer> map;

    public IndexMap(Map<PairwiseComparableObject, Integer> map) {
        this.map = map;
    }

    public void put(PairwiseComparableObject key, Integer value) {
        this.map.put(key, value);
    }

    public Integer get(PairwiseComparableObject key) {
        return this.map.get(key);
    }

    public Set<PairwiseComparableObject> keySet() {
        return this.map.keySet();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
