package com.agh.vacation.gui.eastpack.calculatorgui;

import com.agh.vacation.calculator.Calculator.CalculatorType;

import javax.swing.*;

/**
 * @author Filip Piwosz
 */
public class CalculatorButton extends JRadioButton {
    public final CalculatorType calculatorType;

    CalculatorButton(String text, CalculatorType type) {
        super(text);
        this.calculatorType = type;
    }
}
