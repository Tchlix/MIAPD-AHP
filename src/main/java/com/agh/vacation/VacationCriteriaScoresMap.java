package com.agh.vacation;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Filip Piwosz
 */
class VacationCriteriaScoresMap {
    Map<VacationDestination, CriteriaScores> map;

    VacationCriteriaScoresMap(Map<VacationDestination, CriteriaScores> map) {
        this.map = map;
    }

    Set<Entry<VacationDestination, CriteriaScores>> entrySet() {
        return this.map.entrySet();
    }

}
