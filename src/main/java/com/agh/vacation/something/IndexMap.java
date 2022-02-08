package com.agh.vacation.something;

import java.util.Map;
import java.util.Set;

public class IndexMap<T extends PairwiseComparableObject> {
    private final Map<T, Integer> map;

    public IndexMap(Map<T, Integer> map) {
        this.map = map;
    }

    public void put(T key, Integer value) {
        this.map.put(key, value);
    }

    public Integer get(T key) {
        return this.map.get(key);
    }

    public Set<T> keySet() {
        return this.map.keySet();
    }

    public Set<Map.Entry<T, Integer>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
