package com.agh.vacation.gui.eastpack.choosecontextpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Display list of loaded experts and a button to switch to loaded criteria
 * @author Filip Piwosz
 */
public class ContextPanel extends JPanel {
    public ContextPanel(int width, int height) {
        super();
        this.setBackground(new Color(0xC0C000));
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
    }
}
