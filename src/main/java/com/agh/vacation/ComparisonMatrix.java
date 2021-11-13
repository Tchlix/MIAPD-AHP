package com.agh.vacation;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;

/**
 * @author Filip Piwosz
 */
record ComparisonMatrix(RealMatrix matrix, Map<EnumKeys, Integer> indexMap) {
    ComparisonMatrix(Matrix matrix, Map<EnumKeys, Integer> indexMap) {
        this(matrix.matrix, indexMap);
    }
}
