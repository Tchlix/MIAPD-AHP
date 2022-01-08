package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.ExpertDestinationRatings;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Filip Piwosz
 */
class ExpertRatingsListGUI extends JList<ExpertDestinationRatings> {
    ExpertRatingsListGUI(List<ExpertDestinationRatings> list, int width, int height) {
        //this is how you convert list to array
        super(list.toArray(new ExpertDestinationRatings[0]));
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
    }
}
