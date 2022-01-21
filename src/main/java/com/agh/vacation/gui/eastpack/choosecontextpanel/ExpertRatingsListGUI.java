package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.ExpertDestinationRatings;
import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
class ExpertRatingsListGUI extends JList<ExpertDestinationRatings> implements ListSelectionListener {
    private GeneralMediator mediator;

    ExpertRatingsListGUI(int width, int height, GeneralMediator mediator) {
        //this is how you convert list to array
        super();

        this.addListSelectionListener(this);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        this.mediator = mediator;
        mediator.setExpertRatingList(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = this.getSelectedIndex() == -1 ? 0 : this.getSelectedIndex();
        mediator.setExpertIndex(index);
    }
}
