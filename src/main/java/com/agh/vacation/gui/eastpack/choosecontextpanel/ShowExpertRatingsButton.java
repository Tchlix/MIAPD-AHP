package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Filip Piwosz
 */
class ShowExpertRatingsButton extends JButton implements ActionListener {
    private final GeneralMediator mediator;

    ShowExpertRatingsButton(GeneralMediator mediator) {
        super("SHOW EXPERT RATINGS");
        this.mediator = mediator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            mediator.showExpertRatings();
        }
    }
}
