package com.agh.vacation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates a map with key being VacationDestination and value being score
 *
 * @author Filip Piwosz
 */
class ScoreCalculator {

    Map<VacationDestination, CriteriaScores> calculateCriteriaScores(List<VacationDestination> destinationList,
                                                                     Map<Criterion, ComparisonMatrix<VacationDestination>>
                                                                             comparisonsBasedOnCriteria) {
        Map<VacationDestination, CriteriaScores> result = new HashMap<>();
        EigenvalueCalculator calculator = new EigenvalueCalculator();

        //for every destination put empty map
        destinationList
                .forEach(dest -> result.put(dest, new CriteriaScores(new HashMap<>())));

        //for each criterion put proper value in CriteriaScores for each destination
        comparisonsBasedOnCriteria.forEach((criterion, comparisonMatrix) -> {

            Map<VacationDestination, Double> scoresBasedOnCriterion =
                    calculator.calculateEigenvalues(comparisonMatrix);

            putCriterionScoreForAllDestinations(criterion, scoresBasedOnCriterion, result);
        });
        return result;
    }

    private void putCriterionScoreForAllDestinations(Criterion criterion,
                                                     Map<VacationDestination, Double> scoresBasedOnCriterion,
                                                     Map<VacationDestination, CriteriaScores> result) {
        scoresBasedOnCriterion.forEach(
                (destination, score) -> {
                    CriteriaScores scores = result.get(destination);
                    scores.putScoreFor(criterion, score);
                }
        );
    }
}
