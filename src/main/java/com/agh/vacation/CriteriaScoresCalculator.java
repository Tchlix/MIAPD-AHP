package com.agh.vacation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given List of VacationDestination and ComparisonsBasedOnCriteria
 * calculates a map with key being VacationDestination and value being scores for each criterion (CriteriaScores)
 *
 * @author Filip Piwosz
 */
class CriteriaScoresCalculator {

    VacationCriteriaScoresMap calculateCriteriaScores(List<VacationDestination> destinationList,
                                                      ComparisonsBasedOnCriteria comparisonsBasedOnCriteria) {
        Map<VacationDestination, CriteriaScores> resultMap = new HashMap<>();
        EigenvalueCalculator calculator = new EigenvalueCalculator();

        //for every destination put empty map
        destinationList
                .forEach(dest -> resultMap.put(dest, new CriteriaScores(new HashMap<>())));

        //for each criterion put proper value in CriteriaScores for each destination
        comparisonsBasedOnCriteria.stream().forEach(entry -> {
            Criterion criterion = entry.getKey();
            ComparisonMatrix<VacationDestination> comparisonMatrix = entry.getValue();
            Map<VacationDestination, Double> scoresBasedOnCriterion =
                    calculator.calculateEigenvalues(comparisonMatrix);

            putCriterionScoreForAllDestinations(criterion, scoresBasedOnCriterion, resultMap);
        });
        return new VacationCriteriaScoresMap(resultMap);
    }

    private void putCriterionScoreForAllDestinations(Criterion criterion,
                                                     Map<VacationDestination, Double> scoresBasedOnCriterion,
                                                     Map<VacationDestination, CriteriaScores> resultMap) {
        scoresBasedOnCriterion.forEach(
                (destination, score) -> {
                    CriteriaScores scores = resultMap.get(destination);
                    scores.putScoreFor(criterion, score);
                }
        );
    }
}
