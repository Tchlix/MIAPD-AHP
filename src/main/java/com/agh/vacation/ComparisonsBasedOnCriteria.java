package com.agh.vacation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * @author Filip Piwosz
 */
class ComparisonsBasedOnCriteria {
    private Map<Criterion, ComparisonMatrix<VacationDestination>> map;

    ComparisonsBasedOnCriteria(Map<Criterion, ComparisonMatrix<VacationDestination>> map) {
        this.map = map;
    }

    public void put(Criterion key, ComparisonMatrix<VacationDestination> value) {
        this.map.put(key, value);
    }

    public Stream<Entry<Criterion, ComparisonMatrix<VacationDestination>>> stream() {
        return this.map.entrySet().stream();
    }
}
