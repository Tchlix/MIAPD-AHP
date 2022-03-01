package com.agh.vacation.calculator;

import com.agh.vacation.ds.ComparisonMatrix;
import com.agh.vacation.ds.IndexMap;
import com.agh.vacation.ds.PairwiseComparableObject;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.HashMap;
import java.util.Map;

import static com.agh.vacation.calculator.MathUtilFunctions.maxElementIndex;
import static com.agh.vacation.calculator.MathUtilFunctions.truncateDouble;

class EigenvalueCalculator {
    private static final int DEFAULT_TRUNCATION = 3;

    private EigenvalueCalculator() {
    }

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
     * we can do this, since the respective scale of 2 comparable object values doesn't change
     *
     * @param comparisonMatrix - Pairwise Comparison matrix for criteria
     * @return Map of criteria with respective scaled priorities
     */

    static <T extends PairwiseComparableObject> Map<T, Double> calculateEigenvalues(ComparisonMatrix<T> comparisonMatrix) {
        RealMatrix matrix = comparisonMatrix.matrix();
        IndexMap<T> indexMap = comparisonMatrix.indexMap();
        EigenDecomposition decomposition = new EigenDecomposition(matrix);
        Map<T, Double> result = new HashMap<>();

        RealVector vector = maxEigenValueVector(decomposition);
        applyScaling(vector);
        for (T pairwiseComparableObject : indexMap.keySet()) {
            int index = indexMap.get(pairwiseComparableObject);
            double value = vector.getEntry(index);
            value = truncateDouble(value, DEFAULT_TRUNCATION);
            result.put(pairwiseComparableObject, value);
        }

        return result;
    }

    private static RealVector maxEigenValueVector(EigenDecomposition decomposition) {
        double[] realEigenValues = decomposition.getRealEigenvalues();
        int maxIndex = maxElementIndex(realEigenValues);
        return decomposition.getEigenvector(maxIndex);
    }

    private static void applyScaling(RealVector vector) {
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
