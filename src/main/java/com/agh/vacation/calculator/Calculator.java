package com.agh.vacation.calculator;

import com.agh.vacation.fileloading.CriteriaPrioritiesMap;
import com.agh.vacation.ds.ComparisonMatrix;
import com.agh.vacation.ds.Criterion;
import com.agh.vacation.ds.VacationDestination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mediator between application and different calculators
 */
public class Calculator {
    public enum InconsistencyType {KOCZKODAJ, SAATY, CR}
    public enum CalculatorType {EIGENVALUE, HARKER, GMM}

    private Calculator() {
    }

    public static Result calculateResult(CriteriaPrioritiesMap criteriaPriorities,
                                         VacationCriteriaScoresMap vacationCriteriaScoresMap){
        return ResultCalculator.calculateResult(criteriaPriorities, vacationCriteriaScoresMap);
    }
    public static ComparisonMatricesBasedOnCriteria calculateAIJ(List<Criterion> criteria,
                                                                 List<ComparisonMatricesBasedOnCriteria> expertMatrices) {
        return AIJCalculator.calculateAIJ(criteria, expertMatrices);
    }

    public static Map<Criterion, Double> calculateCriteriaLevelPriorities(ComparisonMatrix<Criterion> matrix) {
        return EigenvalueCalculator.calculateEigenvalues(matrix);
    }

    public static List<Double> calculateInconsistency(InconsistencyType inconsistencyType,
                                                      ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria) {
        return switch (inconsistencyType) {
            case KOCZKODAJ -> InconsistencyCalculator.calculateKoczkodajIndexes(comparisonMatricesBasedOnCriteria);
            case SAATY -> InconsistencyCalculator.calculateSaatyCI(comparisonMatricesBasedOnCriteria);
            case CR -> InconsistencyCalculator.calculateCR(comparisonMatricesBasedOnCriteria);
        };
    }

    /**
     * Given List of VacationDestination and ComparisonMatricesBasedOnCriteria
     * calculates a map with key being VacationDestination and value being scores for each criterion (CriteriaScores)
     */
    public static VacationCriteriaScoresMap calculateCriteriaScores(List<VacationDestination> destinationList,
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
            Map<VacationDestination, Double> scoresBasedOnCriterion = calculateCriteriaScoresCalculator(calculatorType, comparisonMatrix);

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

    private static Map<VacationDestination, Double> calculateCriteriaScoresCalculator(CalculatorType calculatorType,
                                                                                      ComparisonMatrix<VacationDestination> comparisonMatrix) {
        return switch (calculatorType) {
            case GMM -> GMMCalculator.calculateGMMValues(comparisonMatrix);
            case HARKER -> HarkerCalculator.calculateHarkerValues(comparisonMatrix);
            case EIGENVALUE -> EigenvalueCalculator.calculateEigenvalues(comparisonMatrix);
        };
    }
}
