package com.agh.vacation.calculator;

import com.agh.vacation.something.ComparisonMatrix;
import com.agh.vacation.something.PairwiseComparableObject;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;

class HarkerCalculator {
    private static final double NO_VALUE_PRESENT = 0.0d;

    private HarkerCalculator() {
    }

    /**
     * Based on the given incomplete PC matrix C, let us prepare the auxiliary matrix B =[b_ij] such that
     *        {  0           if c_ij = NO_VALUE_PRESENT and i != j
     * b_ij = {  c_ij        if c_ij != NO_VALUE_PRESENT and i != j
     *        {  s_i + 1     if i = j
     * where s_i is the number of missing comparisons in the i-th row of C
     * then I just pass this new matrix to EigenvalueCalculator get priorities
     *
     * Source Understanding_The_Analytic_Hierarchy_Process 4.1.2
     *
     * @param incompleteMatrix with NO_VALUE_PRESENT in empty places
     * @return auxiliary matrix B
     */
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
        RealMatrix matrixB = b(matrix);

        return EigenvalueCalculator.calculateEigenvalues(new ComparisonMatrix<>(matrixB, comparisonMatrix.indexMap()));
    }
}
