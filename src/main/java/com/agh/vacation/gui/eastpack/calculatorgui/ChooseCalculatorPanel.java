package com.agh.vacation.gui.eastpack.calculatorgui;

import com.agh.vacation.CalculatorType;
import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class ChooseCalculatorPanel extends JPanel {

    public ChooseCalculatorPanel(int width, int height, GeneralMediator mediator) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        ButtonGroup group = new ButtonGroup();
        CalculatorButton gmmButton = new CalculatorButton("GMM Calculator", CalculatorType.GMM);
        CalculatorButton eigenvalueButton = new CalculatorButton("Eigenvalue Calculator", CalculatorType.EIGENVALUE);
        eigenvalueButton.doClick();
        CalculatorButton harkerButton = new CalculatorButton("Harker Calculator", CalculatorType.HARKER);
        group.add(gmmButton);
        group.add(eigenvalueButton);
        group.add(harkerButton);
        this.add(gmmButton);
        this.add(eigenvalueButton);
        this.add(harkerButton);
        this.setVisible(true);
        mediator.saveButtonGroup(group);
    }
}
