package com.agh.vacation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given List of VacationDestination and ComparisonMatricesBasedOnCriteria
 * calculates a map with key being VacationDestination and value being scores for each criterion (CriteriaScores)
 *
 * @see VacationCriteriaScoresMap
 */
class CriteriaScoresCalculator {
    private CriteriaScoresCalculator() {
    }

    static VacationCriteriaScoresMap calculateCriteriaScores(List<VacationDestination> destinationList,
                                                             ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria,
                                                             CalculatorType calculatorType) {
        Map<VacationDestination, CriteriaScores> resultMap = new HashMap<>();

        //for every destination put empty map
        destinationList
                .forEach(dest -> resultMap.put(dest, new CriteriaScores(new HashMap<>())));

        //for each criterion put proper value in CriteriaScores for each destination
        comparisonMatricesBasedOnCriteria.stream().forEach(entry -> {
            Criterion criterion = entry.getKey();
            ComparisonMatrix<VacationDestination> comparisonMatrix = entry.getValue();
            Map<VacationDestination, Double> scoresBasedOnCriterion = calculate(calculatorType, comparisonMatrix);

            putCriterionScoreForAllDestinations(criterion, scoresBasedOnCriterion, resultMap);
        });
        return new VacationCriteriaScoresMap(resultMap);
    }

    private static void putCriterionScoreForAllDestinations(Criterion criterion,
                                                            Map<VacationDestination, Double> scoresBasedOnCriterion,
                                                            Map<VacationDestination, CriteriaScores> resultMap) {
        scoresBasedOnCriterion.forEach(
                (destination, score) -> {
                    CriteriaScores scores = resultMap.get(destination);
                    scores.putScoreFor(criterion, score);
                }
        );
    }

    private static Map<VacationDestination, Double> calculate(CalculatorType calculatorType,
                                                              ComparisonMatrix<VacationDestination> comparisonMatrix) {
        return switch (calculatorType) {
            case GMM -> GMMCalculator.calculateGMMValues(comparisonMatrix);
            case HARKER -> HarkerCalculator.calculateHarkerValues(comparisonMatrix);
            case EIGENVALUE -> EigenvalueCalculator.calculateEigenvalues(comparisonMatrix);
        };
    }
}
