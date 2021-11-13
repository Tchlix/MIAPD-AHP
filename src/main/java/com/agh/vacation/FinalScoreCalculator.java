package com.agh.vacation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Filip Piwosz
 */
class FinalScoreCalculator {
    Map<VacationDestination, Double> finalScore(Map<Criterion, Double> criteriaPriorities,
                                                Map<VacationDestination, CriteriaScores> destinationCriteriaRatingsMap) {
        Map<VacationDestination, Double> result = new HashMap<>();
        for (VacationDestination destination : destinationCriteriaRatingsMap.keySet()) {
            CriteriaScores scores = destinationCriteriaRatingsMap.get(destination);
            Double sum = 0.;
            for (Criterion criterion : criteriaPriorities.keySet()) {
                Double score = scores.scoreFor(criterion);
                Double priority = criteriaPriorities.get(criterion);

                sum += score * priority;
            }
            result.put(destination, sum);
        }
        return result;
    }
}
