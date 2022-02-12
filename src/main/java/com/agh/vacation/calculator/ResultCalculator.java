package com.agh.vacation.calculator;

import com.agh.vacation.fileloading.CriteriaPrioritiesMap;
import com.agh.vacation.something.Criterion;
import com.agh.vacation.something.VacationDestination;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
