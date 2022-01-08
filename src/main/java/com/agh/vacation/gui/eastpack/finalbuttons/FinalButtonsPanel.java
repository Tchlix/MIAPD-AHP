package com.agh.vacation.gui.eastpack.finalbuttons;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class FinalButtonsPanel extends JPanel {
    public FinalButtonsPanel(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new CalculateButton());
        this.add(new ViewResultsButton());
    }
}
