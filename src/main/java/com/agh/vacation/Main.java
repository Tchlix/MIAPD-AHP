package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    //EXAMPLE CODE BELOW, THIS IS NOT FINAL
    private static Criterion vfm = new Criterion("Value for money");
    private static Criterion sights = new Criterion("Sights");
    private static Criterion museums = new Criterion("Museums");
    private static Criterion food = new Criterion("Food");
    private static Criterion nl = new Criterion("Night life");

    public static void main(String[] args) {

        //Load criteria
        //TODO: load this from json and remove this example static object
        ComparisonMatrix<Criterion> criteriaComparisonMatrix = criteriaComparisonMatrix();

        //calculate priorities for criteria
        EigenvalueCalculator eigenvalueCalculator = new EigenvalueCalculator();
        CriteriaPrioritiesMap criteriaPriorities =
                new CriteriaPrioritiesMap(eigenvalueCalculator.calculateEigenvalues(criteriaComparisonMatrix));
        List<Criterion> criteria = new ArrayList<>(List.of(vfm, sights, museums, food, nl));
        //Load destinations
        //TODO: load this from json and remove those example static objects
        VacationDestination lisbon = lisbon();
        VacationDestination munich = munich();
        List<VacationDestination> destinations = new ArrayList<>(List.of(lisbon, munich));

        //Create a map of PC matrices based on destination ratings for each criterion
        VacationDestinationComparisonMatricesCreator creator = new VacationDestinationComparisonMatricesCreator();
        ComparisonsBasedOnCriteria comparisonsBasedOnCriteria =
                creator.create(criteria, destinations);

        //for each criterion calculate individual score of each destination
        //(score is not yet multiplied with proper criteria priority value)
        CriteriaScoresCalculator criteriaScoresCalculator = new CriteriaScoresCalculator();
        VacationCriteriaScoresMap criteriaScoresMap =
                criteriaScoresCalculator.calculateCriteriaScores(destinations, comparisonsBasedOnCriteria);

        ResultCalculator resultCalculator = new ResultCalculator();
        Result result = resultCalculator.calculateResult(criteriaPriorities, criteriaScoresMap);
        System.out.println(result.display());

    }

    private static VacationDestination lisbon() {
        Map<Criterion, Integer> lisbonRatings = new HashMap<>();
        lisbonRatings.put(vfm, 4);
        lisbonRatings.put(sights, 5);
        lisbonRatings.put(nl, 4);
        lisbonRatings.put(food, 4);
        lisbonRatings.put(museums, 4);
        return new VacationDestination("Lisbon", lisbonRatings);
    }

    private static VacationDestination munich() {
        Map<Criterion, Integer> munichRatings = new HashMap<>();
        munichRatings.put(vfm, 3);
        munichRatings.put(sights, 4);
        munichRatings.put(nl, 4);
        munichRatings.put(food, 4);
        munichRatings.put(museums, 3);

        return new VacationDestination("Munich", munichRatings);
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
}
