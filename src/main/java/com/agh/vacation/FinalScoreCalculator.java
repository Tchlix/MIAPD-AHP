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
            double sum = 0.;
            for (Map.Entry<Criterion, Double> entry : criteriaPriorities.entrySet()) {
                Double score = scores.scoreFor(entry.getKey());
                Double priority = entry.getValue();

                sum += score * priority;
            }
            result.put(destination, sum);
        }
        return result;
    }
}
