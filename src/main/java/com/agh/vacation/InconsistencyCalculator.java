package com.agh.vacation;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.max;
public class InconsistencyCalculator {
    private static final Double[] RCI = {null, null, null, 0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49, 1.51, 1.48, 1.56, 1.57, 1.59};

    private InconsistencyCalculator() {
    }

    /**
     * Understanding_The_Analytic_Hierarchy_Process 6.3.1
     *
     * @param matrix for which we want to calculate inconsistency
     * @return SaatyCI
     */
    private static double calculateSingleSaatyCI(RealMatrix matrix) {
        EigenDecomposition decomposition = new EigenDecomposition(matrix);
        int n = matrix.getRowDimension();
        double principalEigenvalue = decomposition.getRealEigenvalue(0);
        for (double x : decomposition.getRealEigenvalues()) {
            principalEigenvalue = x > principalEigenvalue ? x : principalEigenvalue;
        }

        return (principalEigenvalue - n) / (n - 1);
    }

    public static List<Double> calculateSaatyCI(ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria) {
        return comparisonMatricesBasedOnCriteria.stream().
                map(s -> s.getValue().matrix()).
                map(InconsistencyCalculator::calculateSingleSaatyCI).toList();
    }

    /**
     * Understanding_The_Analytic_Hierarchy_Process 6.3.2
     *
     * @param comparisonMatricesBasedOnCriteria
     * @return CR values of Saaty CI
     */
    public static List<Double> calculateCR(ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria) {
        LinkedList<Double> ciValues = new LinkedList<>(calculateSaatyCI(comparisonMatricesBasedOnCriteria));
        var size = comparisonMatricesBasedOnCriteria.getIndexMap().keySet().size();
        for (int i = 0; i < ciValues.size(); i++) {
            ciValues.set(i, ciValues.get(i) / RCI[size]);
        }
        return ciValues;
    }

    /**
     * Understanding_The_Analytic_Hierarchy_Process 6.3.8
     *
     * @param matrix for which we want to calculate inconsistency
     * @return global KI
     */
    private static Double calculateSingleKoczkodajIndex(RealMatrix matrix) {
        int size = matrix.getColumnDimension();
        List<Double> localCIList = new LinkedList<>();
        for (int x = 0; x < size; x++) {
            for (int y = x + 1; y < size; y++) {
                for (int z = y + 1; z < size; z++) {
                    Double cXY = matrix.getEntry(x, y);
                    Double cYZ = matrix.getEntry(y, z);
                    Double cXZ = matrix.getEntry(x, z);
                    Double localCI = Math.min(
                            Math.abs(1 - (cXY * cYZ) / cXZ),
                            Math.abs(1 - cXZ / (cXY * cYZ))
                    );
                    localCIList.add(localCI);
                }
            }
        }
        var globalKI = localCIList.stream().max(Comparator.naturalOrder());
        return globalKI.isPresent() ? globalKI.get() : null;
    }

    public static List<Double> calculateKoczkodajIndexes(ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria) {
        return comparisonMatricesBasedOnCriteria.stream().
                map(s -> s.getValue().matrix()).
                map(InconsistencyCalculator::calculateSingleKoczkodajIndex).toList();
    }
}
