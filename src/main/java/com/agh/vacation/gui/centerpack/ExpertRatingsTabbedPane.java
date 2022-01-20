package com.agh.vacation.gui.centerpack;

import com.agh.vacation.ExpertDestinationRatings;

import javax.swing.*;
import java.util.List;

/**
 * @author Filip Piwosz
 */
public class ExpertRatingsTabbedPane extends JTabbedPane {
    public ExpertRatingsTabbedPane(List<ExpertDestinationRatings> expertDestinationRatingsList) {
        expertDestinationRatingsList.forEach(rating ->{
            this.addTab(rating.expertName, new JPanel());
        });
    }
}
