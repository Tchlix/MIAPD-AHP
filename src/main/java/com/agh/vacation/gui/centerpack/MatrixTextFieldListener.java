package com.agh.vacation.gui.centerpack;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * @author Filip Piwosz
 */
class MatrixTextFieldListener {
    private final RealMatrix matrix;
    private final MatrixTextField[][] matrixTextFields;

    MatrixTextFieldListener(RealMatrix matrix, MatrixTextField[][] matrixTextFields) {
        this.matrix = matrix;
        this.matrixTextFields = matrixTextFields;
    }

    void setEntry(int i, int j, double val) {
        matrix.setEntry(i, j, val);
        if (val != 0.0) {
            double another = 1.0 / val;
            matrix.setEntry(j, i, another);
            matrixTextFields[j][i].setText(String.valueOf(another));
        }
    }
}
