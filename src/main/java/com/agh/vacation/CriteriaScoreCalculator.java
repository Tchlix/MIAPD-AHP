package com.agh.vacation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Filip Piwosz
 */
class CriteriaScoreCalculator {

    Map<VacationDestination, CriteriaScores> calculateCriteriaScores(List<VacationDestination> destinationList,
                                                                     Map<Criterion, ComparisonMatrix<VacationDestination>>
                                                                             comparisonMatrixMap) {
        Map<VacationDestination, CriteriaScores> result = new HashMap<>();
        EigenvalueCalculator calculator = new EigenvalueCalculator();
        for (VacationDestination destination : destinationList) {
            result.put(destination, new CriteriaScores(new HashMap<>()));
        }
        for (Entry<Criterion, ComparisonMatrix<VacationDestination>> entry : comparisonMatrixMap.entrySet()) {
            Map<VacationDestination, Double> x = calculator.calculateEigenvalues(entry.getValue(), 3);
            for (Entry<VacationDestination, Double> calcEntry : x.entrySet()) {
                result.get(calcEntry.getKey()).criteriaScores.put(entry.getKey(), calcEntry.getValue());
            }
        }
        return result;
    }
}
