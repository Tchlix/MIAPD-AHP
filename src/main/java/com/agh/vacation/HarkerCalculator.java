package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;

/**
 * Creates new matrixB from incomplete one and then
 * returns priority vector from EigenValueCalculator.calculateEigenvalues
 */
class HarkerCalculator extends IncompleteCalculator {

    private HarkerCalculator() {
    }

    private static RealMatrix b(RealMatrix incompleteMatrix) {
        int dim = incompleteMatrix.getColumnDimension();
        double[][] arrayG = new double[dim][dim];

        for (int y = 0; y < dim; y++) {
            arrayG[y][y] = 1;
            for (int x = 0; x < dim; x++)
                if (y != x) {
                    if (incompleteMatrix.getEntry(y, x) == NO_VALUE_PRESENT) {
                        arrayG[y][y]++;
                    } else {
                        arrayG[y][x] = incompleteMatrix.getEntry(y, x);
                    }
                }
        }
        return new Array2DRowRealMatrix(arrayG);
    }

    static <T extends PairwiseComparableObject> Map<T, Double> calculateHarkerValues(ComparisonMatrix<T> comparisonMatrix) {
        RealMatrix matrix = comparisonMatrix.matrix();
        uncompleteMatrix(matrix);
        RealMatrix matrixB = b(matrix);

        return EigenvalueCalculator.calculateEigenvalues(new ComparisonMatrix<>(matrixB, comparisonMatrix.indexMap()));
    }
}
