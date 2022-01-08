package com.agh.vacation.gui.southpack;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class SouthPanel extends JPanel {
    public SouthPanel(int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(0xFAAACC));
        this.add(new SouthArrowButton("LEFT", new Rectangle(50, 50, 100, 100)));
        this.add(new SouthArrowButton("RIGHT", new Rectangle(150, 50, 100, 100)));
        this.setVisible(true);
    }
}
