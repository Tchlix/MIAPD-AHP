package com.agh.vacation;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author Filip Piwosz
 */
class CriteriaEigenvalueCalculator {

    Map<Criterion, Double> calculateCriteriaEigenValues(CriteriaComparisonMatrix comparisonMatrix) {
        RealMatrix matrix = comparisonMatrix.matrix();
        Map<Criterion, Integer> indexMap = comparisonMatrix.indexMap();
        EigenDecomposition decomposition = new EigenDecomposition(matrix);
        Map<Criterion, Double> result = new EnumMap<>(Criterion.class);
        for (Criterion criterion : Criterion.values()) {
            int index = indexMap.get(criterion);
            Double realEigenvalue = decomposition.getRealEigenvalue(index);
            result.put(criterion, realEigenvalue);
        }
        return result;
    }
}
