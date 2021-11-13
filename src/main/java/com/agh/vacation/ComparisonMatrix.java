package com.agh.vacation;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;

/**
 * @author Filip Piwosz
 */
record ComparisonMatrix(RealMatrix matrix, IndexMap indexMap) {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            builder.append(Arrays.toString(matrix.getRow(i)));
            builder.append(System.lineSeparator());
        }
        builder.append(indexMap.toString());
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
