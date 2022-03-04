package com.agh.vacation.calculator;

import com.agh.vacation.ds.ComparisonMatrix;
import com.agh.vacation.ds.Criterion;
import com.agh.vacation.ds.IndexMap;
import com.agh.vacation.ds.VacationDestination;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * Holds a map of key being Criterion and value being a ComparisonMatrix for vacation destinations
 */
public class ComparisonMatricesBasedOnCriteria {
    private final Map<Criterion, ComparisonMatrix<VacationDestination>> map;

    public ComparisonMatricesBasedOnCriteria(Map<Criterion, ComparisonMatrix<VacationDestination>> map) {
        this.map = map;
    }

    public void put(Criterion key, ComparisonMatrix<VacationDestination> value) {
        this.map.put(key, value);
    }

    public Stream<Entry<Criterion, ComparisonMatrix<VacationDestination>>> stream() {
        return this.map.entrySet().stream();
    }

    RealMatrix getMatrix(Criterion criterion) {
        return map.get(criterion).matrix();
    }

    IndexMap<VacationDestination> getIndexMap() {
        return map.entrySet().iterator().next().getValue().indexMap();
    }
}
