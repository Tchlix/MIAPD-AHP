package com.agh.vacation.gui.centerpack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Filip Piwosz
 */
class MatrixTextField extends JTextField implements ActionListener {
    private final int i;
    private final int j;

    private final MatrixTextFieldListener listener;

    public MatrixTextField(int i, int j, MatrixTextFieldListener listener, double val) {
        super(String.valueOf(val));
        this.i = i;
        this.j = j;
        this.listener = listener;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            double val;
            try {
                val = Double.parseDouble(this.getText());
            } catch (NumberFormatException ignored) {
                val = 0.0;
            }
            listener.setEntry(i, j, val);
        }
    }
}
