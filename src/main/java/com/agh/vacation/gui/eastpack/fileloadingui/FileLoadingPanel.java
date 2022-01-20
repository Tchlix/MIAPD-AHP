package com.agh.vacation.gui.eastpack.fileloadingui;

import com.agh.vacation.gui.CenterPanelMediator;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class FileLoadingPanel extends JPanel {
    public FileLoadingPanel(int width, int height, CenterPanelMediator centerPanelMediator) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(0xB2ACCC));
        this.add(new ChooseCriteriaFileButton(centerPanelMediator));
        this.add(new PickExpertDirectoryButton());
        this.setVisible(true);
    }
}
