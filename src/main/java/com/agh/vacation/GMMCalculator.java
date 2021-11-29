package com.agh.vacation;

import org.apache.commons.math3.linear.*;

import java.util.HashMap;
import java.util.Map;

import static com.agh.vacation.MathUtilFunctions.truncateDouble;

class GMMCalculator extends IncompleteCalculator {
    private static final int DEFAULT_TRUNCATION = 3;

    private GMMCalculator() {
    }

    private static RealMatrix g(RealMatrix incompleteMatrix) {
        int dim = incompleteMatrix.getColumnDimension();
        double[][] arrayG = new double[dim][dim];
        for (int y = 0; y < dim; y++) {
            arrayG[y][y] = dim;
            for (int x = 0; x < dim; x++)
                if (y != x) {
                    if (incompleteMatrix.getEntry(y, x) == NO_VALUE_PRESENT) {
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


    private static RealVector gmm(RealMatrix matrix) {
        uncompleteMatrix(matrix);
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
