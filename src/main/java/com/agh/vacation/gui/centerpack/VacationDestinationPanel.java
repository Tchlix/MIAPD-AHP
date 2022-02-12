package com.agh.vacation.gui.centerpack;

import com.agh.vacation.ds.VacationDestination;

import javax.swing.*;

/**
 * @author Filip Piwosz
 */
class VacationDestinationPanel extends JPanel {
    VacationDestinationPanel(VacationDestination destination){
        this.add(new JTextArea(destination.toString()));
    }
}
