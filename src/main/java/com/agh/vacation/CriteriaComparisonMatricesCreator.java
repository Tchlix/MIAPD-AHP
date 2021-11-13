package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.agh.vacation.RatingDifferenceMapper.mapForAHP;
import static java.lang.Math.abs;

/**
 * Given a list of criteria and list of possible vacation destinations
 * creates a map with key being a criterion and value being a pairwise comparison matrix between destinations
 * @author Filip Piwosz
 */
class CriteriaComparisonMatricesCreator {

    Map<Criterion, ComparisonMatrix> create(List<Criterion> criteria, List<VacationDestination> destinations) {
        Map<Criterion, ComparisonMatrix> result = new HashMap<>();
        IndexMap indexMap = creteIndexMap(destinations);
        for (Criterion criterion : criteria) {
            ComparisonMatrix matrix = createComparisonMatrix(criterion, destinations, indexMap);
            result.put(criterion, matrix);
        }
        return result;
    }

    private IndexMap creteIndexMap(List<VacationDestination> destinations) {
        int destinationSize = destinations.size();
        IndexMap indexMap = new IndexMap(new HashMap<>());
        for (int i = 0; i < destinationSize; i++) {
            VacationDestination destination = destinations.get(i);
            indexMap.put(destination, i);
        }
        return indexMap;
    }

    private ComparisonMatrix createComparisonMatrix(Criterion criterion, List<VacationDestination> destinations,
                                                    IndexMap indexMap) {
        int destinationSize = destinations.size();
        double[][] array = new double[destinationSize][destinationSize];
        for (int i = 0; i < destinationSize; i++) {
            for (int j = i; j < destinationSize; j++) {
                fillSinglePair(criterion, array, i, j, destinations);
            }
        }
        RealMatrix matrix = new Array2DRowRealMatrix(array);
        return new ComparisonMatrix(matrix, indexMap);
    }

    private void fillSinglePair(Criterion criterion, double[][] array, int firstIndex, int secondIndex,
                                List<VacationDestination> destinations) {
        VacationDestination first = destinations.get(firstIndex);
        VacationDestination second = destinations.get(secondIndex);

        Integer firstDestinationRating = first.ratingFor(criterion);
        Integer secondDestinationRating = second.ratingFor(criterion);

        Double value = ahpValueBasedOnDiff(firstDestinationRating, secondDestinationRating);

        if (firstDestinationRating > secondDestinationRating) {
            array[firstIndex][secondIndex] = value;
            array[secondIndex][firstIndex] = 1. / value;
        } else {
            array[firstIndex][secondIndex] = 1. / value;
            array[secondIndex][firstIndex] = value;
        }
    }

    private Double ahpValueBasedOnDiff(Integer firstDestinationRating, Integer secondDestinationRating) {
        Map<Integer, Double> ahpMap = mapForAHP();
        Integer diff = abs(firstDestinationRating - secondDestinationRating);
        return ahpMap.get(diff);
    }
}
