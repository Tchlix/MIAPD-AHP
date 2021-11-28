package com.agh.vacation;

import org.apache.commons.math3.linear.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.agh.vacation.MathUtilFunctions.truncateDouble;

class GMMCalculator {
    private static final Random random = new Random();
    private static final int DEFAULT_TRUNCATION = 3;
    private static final int PERCENTAGE_REMOVED = 30;

    private GMMCalculator() {
    }

    private static RealMatrix g(RealMatrix incompleteMatrix) {
        int dim = incompleteMatrix.getColumnDimension();
        double[][] arrayG = new double[dim][dim];
        for (int y = 0; y < dim; y++) {
            arrayG[y][y] = dim;
            for (int x = 0; x < dim; x++)
                if (y != x) {
                    if (incompleteMatrix.getEntry(y, x) == 0) {
                        arrayG[y][x] = 1;
                        arrayG[y][y]--;
                    } else {
                        arrayG[y][x] = 0;
                    }
                }
        }
        return new Array2DRowRealMatrix(arrayG);
    }

    private static RealVector r(RealMatrix incompleteMatrix) {
        int dim = incompleteMatrix.getColumnDimension();
        double[] r = new double[dim];
        for (int y = 0; y < dim; y++)
            for (int x = 0; x < dim; x++)
                if (incompleteMatrix.getEntry(y, x) != 0)
                    r[y] += Math.log(incompleteMatrix.getEntry(y, x));
        return new ArrayRealVector(r);
    }

    private static RealVector w(RealVector vectorR, RealMatrix matrixG) {
        RealVector vectorW = MatrixUtils.inverse(matrixG).operate(vectorR);
        double sum = 0;
        double value;
        for (int i = 0; i < vectorW.getDimension(); i++) {
            value = Math.pow(Math.E, vectorW.getEntry(i));
            sum += value;
            vectorW.setEntry(i, value);
        }
        if (sum == 0) throw new IllegalArgumentException("Sum of priority vector must be > 0");
        for (int i = 0; i < vectorW.getDimension(); i++) {
            vectorW.setEntry(i, vectorW.getEntry(i) / sum);
        }
        return vectorW;
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

    private static RealVector gmm(RealMatrix matrix) {
        uncompleteMatrix(matrix, (matrix.getRowDimension() * matrix.getRowDimension() - matrix.getRowDimension()) * PERCENTAGE_REMOVED / 200);
        RealMatrix matrixG = g(matrix);
        RealVector vectorR = r(matrix);
        return w(vectorR, matrixG);
    }

    static <T extends PairwiseComparableObject> Map<T, Double> calculateGMMValues(ComparisonMatrix<T> comparisonMatrix) {
        RealMatrix matrix = comparisonMatrix.matrix();
        IndexMap<T> indexMap = comparisonMatrix.indexMap();
        Map<T, Double> result = new HashMap<>();

        RealVector vector = gmm(matrix);
        for (T pairwiseComparableObject : indexMap.keySet()) {
            int index = indexMap.get(pairwiseComparableObject);
            double value = vector.getEntry(index);
            value = truncateDouble(value, DEFAULT_TRUNCATION);
            result.put(pairwiseComparableObject, value);
        }

        return result;
    }
}
