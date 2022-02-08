package com.agh.vacation.calculator;

import com.agh.vacation.something.CriteriaScores;
import com.agh.vacation.something.VacationDestination;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class VacationCriteriaScoresMap {
    private final Map<VacationDestination, CriteriaScores> map;

    public VacationCriteriaScoresMap(Map<VacationDestination, CriteriaScores> map) {
        this.map = map;
    }

    Set<Entry<VacationDestination, CriteriaScores>> entrySet() {
        return this.map.entrySet();
    }

}
