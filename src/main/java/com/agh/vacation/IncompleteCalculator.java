package com.agh.vacation;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Random;

class IncompleteCalculator {
    protected static final int NO_VALUE_PRESENT = 0;
    private static final int PERCENTAGE_REMOVED = 15;
    private static final Random random = new Random();

    protected IncompleteCalculator() {
    }

    //Because with our stars there is no way for incomplete matrix, that's why I made this function to 'uncomplete' one
    //0 in matrix is treated as no value
    protected static void uncompleteMatrix(RealMatrix completeMatrix) {
        int howMany = (completeMatrix.getRowDimension() * completeMatrix.getRowDimension() - completeMatrix.getRowDimension()) * PERCENTAGE_REMOVED / 200;
        int x;
        int y;
        int dim = completeMatrix.getColumnDimension();
        while (howMany > 0) {
            x = random.nextInt(dim);
            y = random.nextInt(dim);
            if (x != y && completeMatrix.getEntry(y, x) != NO_VALUE_PRESENT) {
                completeMatrix.setEntry(y, x, NO_VALUE_PRESENT);
                completeMatrix.setEntry(x, y, NO_VALUE_PRESENT);
                howMany--;
            }
        }
    }
}
