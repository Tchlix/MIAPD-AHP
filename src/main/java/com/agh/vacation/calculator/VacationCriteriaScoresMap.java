package com.agh.vacation.calculator;

import com.agh.vacation.ds.VacationDestination;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class VacationCriteriaScoresMap {
    private final Map<VacationDestination, CriteriaScores> map;

    VacationCriteriaScoresMap(Map<VacationDestination, CriteriaScores> map) {
        this.map = map;
    }

    Set<Entry<VacationDestination, CriteriaScores>> entrySet() {
        return this.map.entrySet();
    }

}
