package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;
import java.util.Random;

/**
 * Creates new matrixB from incomplete one and then
 * returns priority vector from EigenValueCalculator.calculateEigenvalues
 */
class HarkerCalculator {
    private static final Random random = new Random();
    private static final int PERCENTAGE_REMOVED = 30;

    private HarkerCalculator() {
    }

    private static RealMatrix b(RealMatrix incompleteMatrix) {
        int dim = incompleteMatrix.getColumnDimension();
        double[][] arrayG = new double[dim][dim];

        for (int y = 0; y < dim; y++) {
            arrayG[y][y] = 1;
            for (int x = 0; x < dim; x++)
                if (y != x) {
                    if (incompleteMatrix.getEntry(y, x) == 0) {
                        arrayG[y][y]++;
                    } else {
                        arrayG[y][x] = incompleteMatrix.getEntry(y, x);
                    }
                }
        }
        return new Array2DRowRealMatrix(arrayG);
    }

    //Because with our stars there is no way for incomplete matrix, that's why I made this function to 'uncomplete' one
    //0 in matrix is treated as no value
    private static void uncompleteMatrix(RealMatrix completeMatrix, int howMany) {
        int x;
        int y;
        int dim = completeMatrix.getColumnDimension();
        while (howMany > 0) {
            x = random.nextInt(dim);
            y = random.nextInt(dim);
            if (x != y && completeMatrix.getEntry(y, x) != 0) {
                completeMatrix.setEntry(y, x, 0);
                completeMatrix.setEntry(x, y, 0);
                howMany--;
            }
        }
    }

    static <T extends PairwiseComparableObject> Map<T, Double> calculateHarkerValues(ComparisonMatrix<T> comparisonMatrix) {
        RealMatrix matrix = comparisonMatrix.matrix();
        uncompleteMatrix(matrix, (matrix.getRowDimension() * matrix.getRowDimension() - matrix.getRowDimension()) * PERCENTAGE_REMOVED / 200);
        RealMatrix matrixB = b(matrix);

        return EigenvalueCalculator.calculateEigenvalues(new ComparisonMatrix<>(matrixB, comparisonMatrix.indexMap()));
    }
}
