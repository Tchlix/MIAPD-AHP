package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.testng.annotations.Test;

import java.util.EnumMap;
import java.util.Map;

import static com.agh.vacation.Keyword.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Filip Piwosz
 */
public class EigenvalueCalculatorTest {

    @Test
    public void calculateCriteriaPriorities_properCriteriaComparisonMatrix_calculatesExpectedMap() {
        // Given
        RealMatrix matrix = new Array2DRowRealMatrix(new double[][]{
                {1, 3, 5, 7, 9},
                {1. / 3., 1, 3, 5, 7},
                {1. / 5., 1. / 3., 1, 3, 5},
                {1. / 7., 1. / 5., 1. / 3., 1, 3},
                {1. / 9., 1. / 7., 1. / 5., 1. / 3., 1}
        });
        Map<Keyword, Integer> criterionIndexMap = new EnumMap<>(Keyword.class);
        criterionIndexMap.put(VALUE_FOR_MONEY, 0);
        criterionIndexMap.put(NIGHT_LIFE, 1);
        criterionIndexMap.put(SIGHTS, 2);
        criterionIndexMap.put(MUSEUMS, 3);
        criterionIndexMap.put(FOOD, 4);

        ComparisonMatrix comparisonMatrix = new ComparisonMatrix(matrix, criterionIndexMap);

        EigenvalueCalculator calculator = new EigenvalueCalculator();

        //calculated with matrixcalc.org
        Map<Keyword, Double> expected = new EnumMap<>(Keyword.class);
        expected.put(VALUE_FOR_MONEY, .512);
        expected.put(NIGHT_LIFE, .261);
        expected.put(SIGHTS, .128);
        expected.put(MUSEUMS, .063);
        expected.put(FOOD, .033);
        // When
        Map<Keyword, Double> actual = calculator.calculatePriorities(comparisonMatrix, 3);
        // Then
        assertEquals(actual, expected);
    }
}
