package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

record PairwiseMatrix(RealMatrix matrix) {
    PairwiseMatrix(double[][] array) {
        this(new Array2DRowRealMatrix(array));
    }
}
