package com.agh.vacation;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Filip Piwosz
 */
class ResultCalculator {
    private ResultCalculator() {
    }

    static Result calculateResult(CriteriaPrioritiesMap criteriaPriorities,
                                  VacationCriteriaScoresMap vacationCriteriaScoresMap) {
        Map<VacationDestination, Double> resultMap = new HashMap<>();
        for (Entry<VacationDestination, CriteriaScores> destinationScoresEntry : vacationCriteriaScoresMap.entrySet()) {
            VacationDestination destination = destinationScoresEntry.getKey();
            CriteriaScores scores = destinationScoresEntry.getValue();
            double sum = 0.;
            for (Entry<Criterion, Double> criterionDoubleEntry : criteriaPriorities.entrySet()) {
                Double score = scores.scoreFor(criterionDoubleEntry.getKey());
                Double priority = criterionDoubleEntry.getValue();

                sum += score * priority;
            }
            resultMap.put(destination, sum);
        }
        return new Result(resultMap);
    }
}
