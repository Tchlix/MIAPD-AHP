package com.agh.vacation.gui.eastpack.fileloadingui;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Filip Piwosz
 */
class ShowCriteriaButton extends JButton implements ActionListener {
    private GeneralMediator mediator;

    ShowCriteriaButton(GeneralMediator mediator) {
        super("SHOW CRITERIA");
        this.mediator = mediator;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            mediator.showCriteria();
        }
    }
}
