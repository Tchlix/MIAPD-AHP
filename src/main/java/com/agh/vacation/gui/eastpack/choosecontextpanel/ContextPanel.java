package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.ExpertDestinationRatings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Display list of loaded experts and a button to switch to loaded criteria
 *
 * @author Filip Piwosz
 */
public class ContextPanel extends JPanel {
    public ContextPanel(int width, int height) {
        super();
        this.setBackground(new Color(0xC0C000));
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new ExpertRatingsListGUI(new ArrayList<>(List.of(
                new ExpertDestinationRatings(new ArrayList<>()),
                new ExpertDestinationRatings(new ArrayList<>()),
                new ExpertDestinationRatings(new ArrayList<>()),
                new ExpertDestinationRatings(new ArrayList<>()),
                new ExpertDestinationRatings(new ArrayList<>()))),
                width, (int) (height * 0.85f)));
        this.setVisible(true);
    }
}
