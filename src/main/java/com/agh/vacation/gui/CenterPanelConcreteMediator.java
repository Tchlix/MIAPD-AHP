package com.agh.vacation.gui;

import com.agh.vacation.CriteriaPrioritiesMap;
import com.agh.vacation.gui.centerpack.CenterPanel;

import javax.swing.*;

/**
 * @author Filip Piwosz
 */
class CenterPanelConcreteMediator implements CenterPanelMediator {
    private CenterPanel centerPanel;

    CenterPanelConcreteMediator(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    @Override
    public void showCriteria(CriteriaPrioritiesMap criteriaPrioritiesMap) {
        centerPanel.removeAll();
        centerPanel.add(new JTextField(criteriaPrioritiesMap.toString()));
        centerPanel.revalidate();
        System.out.println(criteriaPrioritiesMap);
    }

}
