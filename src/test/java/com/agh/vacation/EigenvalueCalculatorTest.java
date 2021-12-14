package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.agh.vacation.EigenvalueCalculator.calculateEigenvalues;
import static org.testng.Assert.assertEquals;

/**
 *
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
        Criterion vfm = new Criterion("Value for money", new ArrayList<>());
        Criterion sights = new Criterion("Sights", new ArrayList<>());
        Criterion museums = new Criterion("Museums", new ArrayList<>());
        Criterion food = new Criterion("Food", new ArrayList<>());
        Criterion nl = new Criterion("Night life", new ArrayList<>());

        IndexMap<Criterion> criterionIndexMap = new IndexMap<>(new HashMap<>());
        criterionIndexMap.put(vfm, 0);
        criterionIndexMap.put(sights, 1);
        criterionIndexMap.put(museums, 2);
        criterionIndexMap.put(food, 3);
        criterionIndexMap.put(nl, 4);

        ComparisonMatrix<Criterion> comparisonMatrix = new ComparisonMatrix<>(matrix, criterionIndexMap);

        //calculated with matrixcalc.org
        Map<Criterion, Double> expected = new HashMap<>();
        expected.put(vfm, .512);
        expected.put(sights, .261);
        expected.put(museums, .128);
        expected.put(food, .063);
        expected.put(nl, .033);
        // When
        Map<Criterion, Double> actual = calculateEigenvalues(comparisonMatrix);
        // Then
        assertEquals(actual, expected);
    }
}
