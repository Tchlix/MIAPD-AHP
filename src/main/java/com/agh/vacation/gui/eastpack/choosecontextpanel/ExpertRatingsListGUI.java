package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.ExpertDestinationRatings;
import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

/**
 * @author Filip Piwosz
 */
class ExpertRatingsListGUI extends JList<ExpertDestinationRatings> implements ListSelectionListener {
    private GeneralMediator mediator;
    ExpertRatingsListGUI(List<ExpertDestinationRatings> list, int width, int height, GeneralMediator mediator) {
        //this is how you convert list to array
        super();
        this.setListData(list.toArray(new ExpertDestinationRatings[0]));

        this.addListSelectionListener(this);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        this.mediator = mediator;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        mediator.setExpertIndex(this.getSelectedIndex());
    }
}
