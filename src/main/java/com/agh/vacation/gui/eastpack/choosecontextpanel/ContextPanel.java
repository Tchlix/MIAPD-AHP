package com.agh.vacation.gui.eastpack.choosecontextpanel;

import com.agh.vacation.ExpertDestinationRatings;
import com.agh.vacation.gui.GeneralMediator;

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
    public ContextPanel(int width, int height, GeneralMediator mediator) {
        super();
        this.setBackground(new Color(0xC0C000));
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new ExpertRatingsListGUI(new ArrayList<>(List.of(
                new ExpertDestinationRatings("Expert1", new ArrayList<>()),
                new ExpertDestinationRatings("expertName", new ArrayList<>()),
                new ExpertDestinationRatings("expertName", new ArrayList<>()),
                new ExpertDestinationRatings("expertName", new ArrayList<>()),
                new ExpertDestinationRatings("expertName", new ArrayList<>()))),
                width, (int) (height * 0.85f), mediator));
        this.add(new ViewCriteriaButton(mediator));
        this.add(new ShowExpertRatingsButton(mediator));
        this.setVisible(true);
    }
}
