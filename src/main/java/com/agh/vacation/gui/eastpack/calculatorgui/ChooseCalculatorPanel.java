package com.agh.vacation.gui.eastpack.calculatorgui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class ChooseCalculatorPanel extends JPanel {

    public ChooseCalculatorPanel(int width, int height) {
        super();
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(width, height));
        ButtonGroup group = new ButtonGroup();
        JRadioButton gmmButton = new JRadioButton("GMM Calculator");
        JRadioButton eigenvalueButton = new JRadioButton("Eigenvalue Calculator");
        JRadioButton harkerButton = new JRadioButton("Harker Calculator");
        group.add(gmmButton);
        group.add(eigenvalueButton);
        group.add(harkerButton);

        this.add(gmmButton);
        this.add(eigenvalueButton);
        this.add(harkerButton);
        this.setVisible(true);
    }
}
