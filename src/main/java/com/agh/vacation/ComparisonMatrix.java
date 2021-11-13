package com.agh.vacation;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;

/**
 * @author Filip Piwosz
 */
record ComparisonMatrix(RealMatrix matrix, Map<Keyword, Integer> indexMap) {
    ComparisonMatrix(PairwiseMatrix pairwiseMatrix, IndexMap map) {
        this(pairwiseMatrix.matrix, map.map);
    }
}
