package com.agh.vacation;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.Math.*;
import static java.lang.Math.pow;

/**
 * @author Filip Piwosz
 */
class CriteriaEigenvalueCalculator {

    Map<Criterion, Double> calculateCriteriaPriorities(CriteriaComparisonMatrix comparisonMatrix, int truncation) {
        RealMatrix matrix = comparisonMatrix.matrix();
        Map<Criterion, Integer> indexMap = comparisonMatrix.indexMap();
        EigenDecomposition decomposition = new EigenDecomposition(matrix);
        Map<Criterion, Double> result = new EnumMap<>(Criterion.class);
        int n = matrix.getColumnDimension(); //rowDimension also works since we are working with square matrices

        RealVector vector = positiveEigenvalueVector(decomposition, n);
        scaleVectorToOne(vector);

        for (Criterion criterion : indexMap.keySet()) {
            int index = indexMap.get(criterion);
            double priorityValue = vector.getEntry(index);
            priorityValue = truncateDouble(priorityValue, truncation);
            result.put(criterion, priorityValue);
        }

        return result;
    }

    private RealVector positiveEigenvalueVector(EigenDecomposition decomposition, int n) {
        for (int i = 0; i < n; i++) {
            RealVector vector = decomposition.getEigenvector(i);
            if (vectorContainsOnlyPositiveValues(vector)) {
                return vector;
            }
        }
        //according to Perron theorem
        //any positive matrix C has a real and positive eigenvalue
        //which exceeds the moduli of all other eigenvalues
        //program shouldn't reach this line
        throw new IllegalArgumentException();
    }

    private boolean vectorContainsOnlyPositiveValues(RealVector vector) {
        return vector.getMinValue() >= 0.;
    }

    private void scaleVectorToOne(RealVector vector) {
        double sum = 0;
        int n = vector.getDimension();
        for (int i = 0; i < n; i++) {
            sum += vector.getEntry(i);
        }
        for (int i = 0; i < n; i++) {
            double entry = vector.getEntry(i);
            vector.setEntry(i, entry / sum);
        }
    }

    private double truncateDouble(double value, int truncation) {
        double tenToPower = pow(10, truncation);
        return floor(value * tenToPower) / tenToPower;
    }
}
