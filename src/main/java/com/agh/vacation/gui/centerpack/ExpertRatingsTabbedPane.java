package com.agh.vacation.gui.centerpack;

import com.agh.vacation.ExpertDestinationRatings;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class ExpertRatingsTabbedPane extends JTabbedPane {
    public ExpertRatingsTabbedPane(Dimension preferredSize, ExpertDestinationRatings expertDestinationRatings) {
        this.setPreferredSize(preferredSize);
        expertDestinationRatings.ratings.forEach(dest -> this.addTab(dest.name, new VacationDestinationPanel(dest)));
        this.revalidate();
    }
}
