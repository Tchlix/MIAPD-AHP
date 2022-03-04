package com.agh.vacation.gui.eastpack.fileloadingui;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Filip Piwosz
 */
class ShowComparisonMatricesButton extends JButton implements ActionListener {
    private final GeneralMediator mediator;

    ShowComparisonMatricesButton(GeneralMediator mediator) {
        super("SHOW COMPARISON MATRICES");
        this.mediator = mediator;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            mediator.showExpertMatrices();
        }
    }
}
