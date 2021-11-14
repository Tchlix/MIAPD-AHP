package com.agh.vacation;

import java.util.Map;
import java.util.Set;

/**
 * @author Filip Piwosz
 */
class IndexMap<T extends PairwiseComparableObject> {
    private final Map<T, Integer> map;

    public IndexMap(Map<T, Integer> map) {
        this.map = map;
    }

    public void put(T key, Integer value) {
        this.map.put(key, value);
    }

    public Integer get(Object key) {
        return this.map.get(key);
    }

    public Set<T> keySet() {
        return this.map.keySet();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
