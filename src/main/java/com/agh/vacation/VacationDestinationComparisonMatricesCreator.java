package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.HashMap;
import java.util.List;

import static com.agh.vacation.RatingDifferenceMapper.mapForAHP;
import static java.lang.Math.abs;

/**
 * Given a list of criteria and list of possible vacation destinations
 * creates a map of ComparisonMatricesBasedOnCriteria object
 *
 * @see ComparisonMatricesBasedOnCriteria
 */
public class VacationDestinationComparisonMatricesCreator {
    private VacationDestinationComparisonMatricesCreator() {
    }

    public static ComparisonMatricesBasedOnCriteria createComparisonMatricesBasedOnCriteria(List<Criterion> criteria,
                                                                                            List<VacationDestination> destinations) {
        ComparisonMatricesBasedOnCriteria result = new ComparisonMatricesBasedOnCriteria(new HashMap<>());
        IndexMap<VacationDestination> indexMap = createIndexMap(destinations);
        criteria
                .forEach(criterion -> {
                    ComparisonMatrix<VacationDestination> matrix =
                            createComparisonMatrix(criterion, destinations, indexMap);
                    result.put(criterion, matrix);
                });
        return result;
    }

    private static IndexMap<VacationDestination> createIndexMap(List<VacationDestination> destinations) {
        int destinationSize = destinations.size();
        IndexMap<VacationDestination> indexMap = new IndexMap<>(new HashMap<>());
        for (int i = 0; i < destinationSize; i++) {
            VacationDestination destination = destinations.get(i);
            indexMap.put(destination, i);
        }
        return indexMap;
    }

    private static ComparisonMatrix<VacationDestination> createComparisonMatrix(Criterion criterion, List<VacationDestination> destinations,
                                                                                IndexMap<VacationDestination> indexMap) {
        int destinationSize = destinations.size();
        double[][] array = new double[destinationSize][destinationSize];
        for (int i = 0; i < destinationSize; i++) {
            for (int j = i; j < destinationSize; j++) {
                fillSinglePair(criterion, array, i, j, destinations);
            }
        }
        RealMatrix matrix = new Array2DRowRealMatrix(array);
        return new ComparisonMatrix<>(matrix, indexMap);
    }

    private static void fillSinglePair(Criterion criterion, double[][] array, int firstIndex, int secondIndex,
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

    private static Double ahpValueBasedOnDiff(Integer firstDestinationRating, Integer secondDestinationRating) {
        Integer diff = abs(firstDestinationRating - secondDestinationRating);
        return mapForAHP.get(diff);
    }
}
