package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Filip Piwosz
 */
class ViewCriteriaButton extends JButton implements ActionListener {
    private GeneralMediator mediator;

    ViewCriteriaButton(GeneralMediator mediator) {
        super("SHOW CRITERIA");
        this.mediator = mediator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            mediator.showCriteria();
        }
    }
}
