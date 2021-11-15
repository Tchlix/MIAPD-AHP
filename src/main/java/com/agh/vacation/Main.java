package com.agh.vacation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.agh.vacation.CriteriaScoresCalculator.calculateCriteriaScores;
import static com.agh.vacation.EigenvalueCalculator.calculateEigenvalues;
import static com.agh.vacation.ResultCalculator.calculateResult;
import static com.agh.vacation.VacationDestinationComparisonMatricesCreator.createComparisonMatricesBasedOnCriteria;

public class Main {
    private static final String CITIES_PATH = "Cities";
    //EXAMPLE CODE BELOW, THIS IS NOT FINAL
    private static final Criterion vfm = new Criterion("Value for money");
    private static final Criterion sights = new Criterion("Sights");
    private static final Criterion museums = new Criterion("Museums");
    private static final Criterion food = new Criterion("Food");
    private static final Criterion nl = new Criterion("Night life");

    public static void main(String[] args) {

        //Load criteria
        //TODO: load this from json and remove this example static object
        ComparisonMatrix<Criterion> criteriaComparisonMatrix = criteriaComparisonMatrix();
        //calculate priorities for criteria
        CriteriaPrioritiesMap criteriaPriorities =
                new CriteriaPrioritiesMap(calculateEigenvalues(criteriaComparisonMatrix));
        List<Criterion> criteria = new ArrayList<>(List.of(vfm, sights, museums, food, nl));

        //Load destinations
        List<VacationDestination> destinations = loadDestinations();
        if (destinations.size() == 0) {
            System.err.println("No cities !");
            return;
        }
        //Create a map of PC matrices based on destination ratings for each criterion
        ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria =
                createComparisonMatricesBasedOnCriteria(criteria, destinations);

        //for each criterion calculate individual score of each destination
        //(score is not yet multiplied with proper criteria priority value)
        VacationCriteriaScoresMap criteriaScoresMap =
                calculateCriteriaScores(destinations, comparisonMatricesBasedOnCriteria);

        Result result = calculateResult(criteriaPriorities, criteriaScoresMap);
        System.out.println(result.display());

    }


    private static ComparisonMatrix<Criterion> criteriaComparisonMatrix() {
        RealMatrix criteriaMatrix = new Array2DRowRealMatrix(new double[][]{
                {1, 3, 5, 7, 9},
                {1. / 3., 1, 3, 5, 7},
                {1. / 5., 1. / 3., 1, 3, 5},
                {1. / 7., 1. / 5., 1. / 3., 1, 3},
                {1. / 9., 1. / 7., 1. / 5., 1. / 3., 1}
        });
        IndexMap<Criterion> indexMap = new IndexMap<>(new HashMap<>());
        indexMap.put(vfm, 0);
        indexMap.put(sights, 1);
        indexMap.put(museums, 2);
        indexMap.put(food, 3);
        indexMap.put(nl, 4);

        return new ComparisonMatrix<>(criteriaMatrix, indexMap);
    }

    static List<VacationDestination> loadDestinations() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<VacationDestination> destinations = new ArrayList<>();
        try {
            Files.list(Paths.get(CITIES_PATH))
                    .map(Path::toFile)
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
