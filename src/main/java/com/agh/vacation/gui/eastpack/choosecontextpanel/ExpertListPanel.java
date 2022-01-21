package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.*;

/**
 * Display list of loaded experts and a button to switch to loaded criteria
 *
 * @author Filip Piwosz
 */
public class ExpertListPanel extends JPanel {
    public ExpertListPanel(int width, int height, GeneralMediator mediator) {
        super();
        this.setBackground(new Color(0xC0C000));
        this.setPreferredSize(new Dimension(width, height));
        this.add(new ExpertRatingsListGUI(
                width, (int) (height * 0.85f), mediator));
        this.setVisible(true);
    }
}
