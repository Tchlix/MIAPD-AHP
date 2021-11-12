package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * @author Filip Piwosz
 */
public class CriteriaEigenvalueCalculatorTest {

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
        Map<Criterion, Integer> criterionIndexMap = new EnumMap<>(Criterion.class);
        criterionIndexMap.put(Criterion.VALUE_FOR_MONEY, 0);
        criterionIndexMap.put(Criterion.NIGHT_LIFE, 1);
        criterionIndexMap.put(Criterion.SIGHTS, 2);
        criterionIndexMap.put(Criterion.MUSEUMS, 3);
        criterionIndexMap.put(Criterion.FOOD, 4);

        CriteriaComparisonMatrix comparisonMatrix = new CriteriaComparisonMatrix(matrix, criterionIndexMap);

        CriteriaEigenvalueCalculator calculator = new CriteriaEigenvalueCalculator();

        //calculated with matrixcalc.org
        Map<Criterion, Double> expected = new EnumMap<>(Criterion.class);
        expected.put(Criterion.VALUE_FOR_MONEY, .512);
        expected.put(Criterion.NIGHT_LIFE, .261);
        expected.put(Criterion.SIGHTS, .128);
        expected.put(Criterion.MUSEUMS, .063);
        expected.put(Criterion.FOOD, .033);
        // When
        Map<Criterion, Double> actual = calculator.calculateCriteriaPriorities(comparisonMatrix, 3);
        // Then
        assertEquals(actual, expected);
    }
}
