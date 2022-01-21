package com.agh.vacation.gui.centerpack;

import com.agh.vacation.ComparisonMatrix;
import com.agh.vacation.VacationDestination;
import org.apache.commons.math3.linear.RealMatrix;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;

/**
 * @author Filip Piwosz
 */
class ComparisonMatrixPanel extends JPanel {
    ComparisonMatrixPanel(Dimension preferredSize, ComparisonMatrix<VacationDestination> comparisonMatrix) {
        this.setPreferredSize(preferredSize);
        int size = comparisonMatrix.matrix().getRowDimension() + 1;
        this.setLayout(new GridLayout(size, size, 10, 10));
        this.add(new JPanel());
        List<Map.Entry<VacationDestination, Integer>> entries = new ArrayList<>(comparisonMatrix
                .indexMap()
                .entrySet()
                .stream()
                .toList());
        entries.sort(comparingByValue());
        for (Map.Entry<VacationDestination, Integer> entry : entries) {
            JTextField jTextField = new JTextField(entry.getKey().name);
            jTextField.setEditable(false);
            this.add(jTextField);
        }
        RealMatrix matrix = comparisonMatrix.matrix();
        MatrixTextField[][] matrixTextFields = new MatrixTextField[entries.size()][entries.size()];
        MatrixTextFieldListener listener = new MatrixTextFieldListener(matrix, matrixTextFields);
        for (int i = 0; i < entries.size(); i++) {
            JTextField jTextField = new JTextField(entries.get(i).getKey().name);
            jTextField.setEditable(false);
            this.add(jTextField);
            for (int j = 0; j < entries.size(); j++) {
                double val = matrix.getEntry(i, j);
                MatrixTextField matrixTextField = new MatrixTextField(i, j, listener, val);
                matrixTextFields[i][j] = matrixTextField;
                this.add(matrixTextField);
            }
        }
    }
}
