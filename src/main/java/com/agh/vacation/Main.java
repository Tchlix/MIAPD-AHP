package com.agh.vacation;

import com.agh.vacation.calculator.*;
import com.agh.vacation.ds.Criterion;
import com.agh.vacation.fileloading.CriteriaJSONLoader;
import com.agh.vacation.fileloading.CriteriaPrioritiesMap;
import com.agh.vacation.fileloading.ExpertDestinationRatings;
import com.agh.vacation.gui.MainFrame;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.agh.vacation.calculator.Calculator.InconsistencyType.*;
import static com.agh.vacation.calculator.Calculator.calculateCriteriaScores;
import static com.agh.vacation.ds.VacationDestinationComparisonMatricesCreator.createComparisonMatricesBasedOnCriteria;
import static com.agh.vacation.fileloading.DestinationLoader.loadMultipleExpertsDestinationRatings;

public class Main {
    private static final String CRITERIA_PATH = "criteria.json";

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("gui")) {
            new MainFrame();
            return;
        }
        //Load criteria and destinations
        CriteriaPrioritiesMap criteriaPriorities;
        List<ExpertDestinationRatings> multipleExpertsDestinationRatings;

        try {
            criteriaPriorities = CriteriaJSONLoader.loadCriteria(Path.of(CRITERIA_PATH));
            multipleExpertsDestinationRatings = loadMultipleExpertsDestinationRatings();
        } catch (IOException e) {
            Logging.error("Couldn't load parameter(s)!");
            Logging.error(e.getMessage());
            return;
        }

        List<Criterion> criteriaList = criteriaPriorities.
                keySet().
                stream().
                toList();


        //Create a list of maps of PC matrices based on destination ratings for each criterion
        List<ComparisonMatricesBasedOnCriteria> expertMatrices = multipleExpertsDestinationRatings.stream().
                map(expert -> createComparisonMatricesBasedOnCriteria(criteriaList, expert.ratings)).toList();

        ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria = Calculator.calculateAIJ(criteriaList, expertMatrices);

        //Variable for cities names
        var destinations = multipleExpertsDestinationRatings.get(0).ratings;

        //for each criterion calculate individual score of each destination
        //(score is not yet multiplied with proper criteria priority value)
        VacationCriteriaScoresMap criteriaScoresMap =
                calculateCriteriaScores(destinations, comparisonMatricesBasedOnCriteria,
                        Calculator.CalculatorType.EIGENVALUE);

        //calculate final result - sum(score * criterion priority)
        Result result = Calculator.calculateResult(criteriaPriorities, criteriaScoresMap);
        Logging.info(result);
        //Calculate inconsistency
        var inconsistencyResult = new InconsistencyResult(
                Calculator.calculateInconsistency(SAATY, comparisonMatricesBasedOnCriteria),
                Calculator.calculateInconsistency(CR, comparisonMatricesBasedOnCriteria),
                Calculator.calculateInconsistency(KOCZKODAJ, comparisonMatricesBasedOnCriteria),
                criteriaList);
        Logging.info(inconsistencyResult);
    }
}
