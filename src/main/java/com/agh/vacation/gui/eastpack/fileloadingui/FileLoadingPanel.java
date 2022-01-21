package com.agh.vacation.gui.eastpack.fileloadingui;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class FileLoadingPanel extends JPanel {
    public FileLoadingPanel(int width, int height, GeneralMediator generalMediator) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(0xB2ACCC));
        this.add(new ChooseCriteriaFileButton(generalMediator));
        this.add(new PickExpertDirectoryButton(generalMediator));
        this.add(new ShowCriteriaButton(generalMediator));
        this.add(new ShowExpertRatingsButton(generalMediator));
        this.add(new ShowComparisonMatricesButton(generalMediator));
        this.setVisible(true);
    }
}
