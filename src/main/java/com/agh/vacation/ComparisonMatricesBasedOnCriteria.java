package com.agh.vacation;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * Holds a map of key being Criterion and value being a ComparisonMatrix for vacation destinations
 */
class ComparisonMatricesBasedOnCriteria {
    private final Map<Criterion, ComparisonMatrix<VacationDestination>> map;

    ComparisonMatricesBasedOnCriteria(Map<Criterion, ComparisonMatrix<VacationDestination>> map) {
        this.map = map;
    }

    public void put(Criterion key, ComparisonMatrix<VacationDestination> value) {
        this.map.put(key, value);
    }

    public Stream<Entry<Criterion, ComparisonMatrix<VacationDestination>>> stream() {
        return this.map.entrySet().stream();
    }

    public RealMatrix getMatrix(Criterion criterion) {
        return map.get(criterion).matrix();
    }

    public IndexMap<VacationDestination> getIndexMap() {
        return map.entrySet().iterator().next().getValue().indexMap();
    }
}
