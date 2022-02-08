package com.agh.vacation.calculator;

import com.agh.vacation.something.ComparisonMatrix;
import com.agh.vacation.something.IndexMap;
import com.agh.vacation.something.PairwiseComparableObject;
import org.apache.commons.math3.linear.*;

import java.util.HashMap;
import java.util.Map;

import static com.agh.vacation.calculator.MathUtilFunctions.truncateDouble;

class GMMCalculator {
    private static final int DEFAULT_TRUNCATION = 3;
    private static final double NO_VALUE_PRESENT = 0.0;

    private GMMCalculator() {
    }

    /**
     * Based on the given incomplete PC matrix C, let us prepare the auxiliary matrix G =[g_ij] such that
     *        {  1           if c_ij = NO_VALUE_PRESENT and i != j
     * g_ij = {  0           if c_ij != NO_VALUE_PRESENT and i != j
     *        {  n - s_i     if i = j
     * where s_i is the number of missing comparisons in the i-th row of C
     *
     * Source Understanding_The_Analytic_Hierarchy_Process 4.2.2
     *
     * @param incompleteMatrix with NO_VALUE_PRESENT in empty places
     * @return auxiliary matrix G
     */
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

    /**
     * We also create constant term vector
     *     {    Σ(from j = 1 to n) ln(c_1j) }
     * r = {    ........................... }
     *     {    Σ(from j = 1 to n) ln(c_nj) }
     * we skip over c_nj = NO_VALUE_PRESENT
     *
     * Source Understanding_The_Analytic_Hierarchy_Process 4.2.2 (4.8)
     *
     * @param incompleteMatrix with NO_VALUE_PRESENT in empty places
     * @return RealVector r - constant term vector
     */
    private static RealVector r(RealMatrix incompleteMatrix) {
        int dim = incompleteMatrix.getColumnDimension();
        double[] r = new double[dim];
        for (int y = 0; y < dim; y++)
            for (int x = 0; x < dim; x++)
                if (incompleteMatrix.getEntry(y, x) != NO_VALUE_PRESENT)
                    r[y] += Math.log(incompleteMatrix.getEntry(y, x));
        return new ArrayRealVector(r);
    }

    /**
     * Then we solve the linear equation system
     * Gŵ = r
     * and create the ranking vector w in the form
     *      {e^ŵ(a_1) }
     * w =  {........ }
     *      {e^ŵ(a_n) }
     *
     * at last, we rescale w so that all entries sum to 1
     *
     *        {       w(a_1)            }
     *        { --------------------    }
     *        { Σ( j = 1 to n) w(a_j)   }
     *        {.....................    }
     * w_gm = {       w(a_n)            }
     *        { --------------------    }
     *        { Σ( j = 1 to n) w(a_j)   }
     *
     * Source Understanding_The_Analytic_Hierarchy_Process 4.2.2 (4.9)
     *
     * @param vectorR constant term vector
     * @param matrixG auxiliary matrix G
     * @return ranking vector w
     */
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
