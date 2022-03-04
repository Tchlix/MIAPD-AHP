package com.agh.vacation.gui.eastpack.finalbuttons;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Filip Piwosz
 */
class CalculateButton extends JButton implements ActionListener {
    private final GeneralMediator mediator;

    CalculateButton(GeneralMediator mediator) {
        super("CALCULATE!");
        this.mediator = mediator;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            mediator.calculateResults();
        }
    }
}
