package com.agh.vacation;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.EnumMap;
import java.util.Map;

import static com.agh.vacation.MathUtilFunctions.maxElementIndex;
import static com.agh.vacation.MathUtilFunctions.truncateDouble;

/**
 * @author Filip Piwosz
 */
class CriteriaEigenvalueCalculator {

    /**
     * https://en.wikipedia.org/wiki/Perron%E2%80%93Frobenius_theorem
     * According to Perron-Frobenius theorem:
     * "[...] the Perron–Frobenius theorem [...] asserts that a real square matrix with positive entries
     * has a unique largest real eigenvalue and that the corresponding eigenvector can be chosen to have
     * strictly positive components[...]"
     * A bit of explanation:
     * decomposition.getEigenvector(int i) returns some solution to the eigenvalue equation
     * which can sometimes contain negative values
     * from the theorem we know that the eigenvector corresponding to the maximum eigenvalue can be chosen to have
     * only positive values
     * we apply a little trick - 1. swap all values in the eigenvector to its absolute values
     * 2. scale them so that they all sum up to 1.0
     * we can do this, since the respective scale of 2 criteria values doesn't change
     *
     * @param comparisonMatrix - Pairwise Comparison matrix for criteria
     * @param truncation       - truncation for double type values
     * @return Map of criteria with respective scaled priorities
     */

    Map<Criterion, Double> calculateCriteriaPriorities(CriteriaComparisonMatrix comparisonMatrix, int truncation) {
        RealMatrix matrix = comparisonMatrix.matrix();
        Map<Criterion, Integer> indexMap = comparisonMatrix.indexMap();
        EigenDecomposition decomposition = new EigenDecomposition(matrix);
        Map<Criterion, Double> result = new EnumMap<>(Criterion.class);

        RealVector vector = maxEigenValueVector(decomposition);
        applyScaling(vector);

        for (Criterion criterion : indexMap.keySet()) {
            int index = indexMap.get(criterion);
            double priorityValue = vector.getEntry(index);
            priorityValue = truncateDouble(priorityValue, truncation);
            result.put(criterion, priorityValue);
        }

        return result;
    }

    private RealVector maxEigenValueVector(EigenDecomposition decomposition) {
        double[] realEigenValues = decomposition.getRealEigenvalues();
        int maxIndex = maxElementIndex(realEigenValues);
        return decomposition.getEigenvector(maxIndex);
    }

    private void applyScaling(RealVector vector) {
        double sum = 0;
        int vectorDimension = vector.getDimension();
        for (int i = 0; i < vectorDimension; i++) {
            double entry = vector.getEntry(i);
            entry = Math.abs(entry);
            vector.setEntry(i, entry);
            sum += entry;
        }
        if (sum == 0.) {
            return;
        }
        for (int i = 0; i < vectorDimension; i++) {
            double entry = vector.getEntry(i);
            vector.setEntry(i, entry / sum);
        }
    }
}
