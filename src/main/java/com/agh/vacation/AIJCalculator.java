package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AIJCalculator {
    private AIJCalculator() {
    }

    /**
     * We just return geometric mean of matrices
     *
     * @param criteria       list of criteria for loop
     * @param expertMatrices multiple matrices to be aggregated
     * @return single matrix calculated from geometric mean
     */
    static ComparisonMatricesBasedOnCriteria calculate(List<Criterion> criteria, List<ComparisonMatricesBasedOnCriteria> expertMatrices) {
        if (expertMatrices.size() == 1)
            return expertMatrices.get(0);

        Map<Criterion, ComparisonMatrix<VacationDestination>> map = new HashMap<>();
        var indexMap = expertMatrices.get(0).getIndexMap();
        var matrixSize = indexMap.keySet().size();
        for (final Criterion criterion : criteria) {
            RealMatrix resultMatrix = new Array2DRowRealMatrix(matrixSize, matrixSize);
            List<RealMatrix> matrices = expertMatrices.stream().map(s -> s.getMatrix(criterion)).toList();
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    double newValue = 1;
                    for (RealMatrix oneOfMatrices : matrices) {
                        newValue *= oneOfMatrices.getEntry(i, j);
                    }
                    newValue = Math.pow(newValue, 1.0 / matrices.size());
                    resultMatrix.setEntry(i, j, newValue);
                }
            }
            ComparisonMatrix<VacationDestination> comparisonMatrix = new ComparisonMatrix<>(resultMatrix, indexMap);
            map.put(criterion, comparisonMatrix);
        }
        return new ComparisonMatricesBasedOnCriteria(map);
    }
}
