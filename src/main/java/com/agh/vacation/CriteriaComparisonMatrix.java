package com.agh.vacation;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Map;

/**
 * @author Filip Piwosz
 */
record CriteriaComparisonMatrix(RealMatrix matrix, Map<Criterion, Integer> indexMap) {
}
