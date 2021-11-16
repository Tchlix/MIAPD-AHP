package com.agh.vacation;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.agh.vacation.CriteriaJSONLoader.loadCriteria;
import static com.agh.vacation.CriteriaScoresCalculator.calculateCriteriaScores;
import static com.agh.vacation.EigenvalueCalculator.calculateEigenvalues;
import static com.agh.vacation.ResultCalculator.calculateResult;
import static com.agh.vacation.VacationDestinationComparisonMatricesCreator.createComparisonMatricesBasedOnCriteria;

public class Main {
    private static final String CITIES_PATH = "Cities";
    private static final String CRITERIA_PATH = "criteria.json";

    public static void main(String[] args) {
        //Load criteria and destinations
        ComparisonMatrix<Criterion> criteriaComparisonMatrix;
        List<VacationDestination> destinations;

        try {
            criteriaComparisonMatrix = loadCriteria(Path.of(CRITERIA_PATH));
            destinations = loadDestinations();
        } catch (IOException e) {
            System.err.println("Couldn't load parameter(s)!");
            System.err.println(e.getMessage());
            return;
        }
        //calculate priorities for criteria
        CriteriaPrioritiesMap criteriaPriorities =
                new CriteriaPrioritiesMap(calculateEigenvalues(criteriaComparisonMatrix));

        List<Criterion> criteriaList = criteriaComparisonMatrix.
                indexMap().
                keySet().
                stream().
                toList();

        //Create a map of PC matrices based on destination ratings for each criterion
        ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria =
                createComparisonMatricesBasedOnCriteria(criteriaList, destinations);

        //for each criterion calculate individual score of each destination
        //(score is not yet multiplied with proper criteria priority value)
        VacationCriteriaScoresMap criteriaScoresMap =
                calculateCriteriaScores(destinations, comparisonMatricesBasedOnCriteria);

        //calculate final result - sum(score * criterion priority)
        Result result = calculateResult(criteriaPriorities, criteriaScoresMap);
        System.out.println(result.display());

    }

    static List<VacationDestination> loadDestinations() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<VacationDestination> destinations = new ArrayList<>();
        try (Stream<Path> pathStream = Files.list(Paths.get(CITIES_PATH))) {
            pathStream.map(Path::toFile)
                    .forEach(path -> {
                        try {
                            destinations.add(objectMapper.readValue(path, VacationDestination.class));
                        } catch (IOException e) {
                            System.err.println("There was a problem with: " + path);
                        }
                    });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return destinations;
    }
}
