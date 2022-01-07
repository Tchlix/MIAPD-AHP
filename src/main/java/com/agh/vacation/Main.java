package com.agh.vacation;

import com.agh.vacation.fileloading.CriteriaJSONLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static com.agh.vacation.CriteriaScoresCalculator.calculateCriteriaScores;
import static com.agh.vacation.DestinationLoader.loadMultipleExpertsDestinationRatings;
import static com.agh.vacation.ResultCalculator.calculateResult;
import static com.agh.vacation.VacationDestinationComparisonMatricesCreator.createComparisonMatricesBasedOnCriteria;

public class Main {
    private static final String CRITERIA_PATH = "criteria.json";

    public static void main(String[] args) {
        //Load criteria and destinations
        CriteriaPrioritiesMap criteriaPriorities;
        List<List<VacationDestination>> multipleExpertsDestinationRatings;

        try {
            //criteriaComparisonMatrix = loadCriteria(Path.of(CRITERIA_PATH));
            criteriaPriorities = CriteriaJSONLoader.loadCriteria(Path.of(CRITERIA_PATH));
            multipleExpertsDestinationRatings = loadMultipleExpertsDestinationRatings();
        } catch (IOException e) {
            System.err.println("Couldn't load parameter(s)!");
            System.err.println(e.getMessage());
            return;
        }

        List<Criterion> criteriaList = criteriaPriorities.
                keySet().
                stream().
                toList();


        //Create a list of maps of PC matrices based on destination ratings for each criterion
        List<ComparisonMatricesBasedOnCriteria> expertMatrices = multipleExpertsDestinationRatings.stream().
                map(destinationsRating -> createComparisonMatricesBasedOnCriteria(criteriaList, destinationsRating)).toList();

        ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria = AIJCalculator.calculate(criteriaList, expertMatrices);

        //Variable for cities names
        var destinations = multipleExpertsDestinationRatings.get(0);

        //for each criterion calculate individual score of each destination
        //(score is not yet multiplied with proper criteria priority value)
        VacationCriteriaScoresMap criteriaScoresMap =
                calculateCriteriaScores(destinations, comparisonMatricesBasedOnCriteria, CalculatorType.EIGENVALUE);

        //calculate final result - sum(score * criterion priority)
        Result result = calculateResult(criteriaPriorities, criteriaScoresMap);
        System.out.println(result.display());
        //Calculate inconsistency
        System.out.println(comparisonMatricesBasedOnCriteria.stream().map(Map.Entry::getKey).toList());
        System.out.println("Saaty CI: " + InconsistencyCalculator.calculateSaatyCI(comparisonMatricesBasedOnCriteria));
        System.out.println("CR: " + InconsistencyCalculator.calculateCR(comparisonMatricesBasedOnCriteria));
        System.out.println("Koczkodaj Index: " + InconsistencyCalculator.calculateKoczkodajIndexes(comparisonMatricesBasedOnCriteria));
    }
}
