package com.agh.vacation.gui;

import com.agh.vacation.gui.centerpack.CenterPanel;
import com.agh.vacation.gui.eastpack.EastPanel;
import com.agh.vacation.gui.southpack.SouthPanel;

import javax.swing.*;
import java.awt.*;

import static com.agh.vacation.gui.GUIUtils.scaledHeight;
import static com.agh.vacation.gui.GUIUtils.scaledWidth;

/**
 * @author Filip Piwosz
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super();
        this.setSize(1200, 750);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new CenterPanel(), BorderLayout.CENTER);
        this.add(new EastPanel(scaledWidth(this, 0.3f), this.getHeight()),
                BorderLayout.EAST);
        this.add(new SouthPanel(this.getWidth(), scaledHeight(this, 0.15f)),
                BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);

    }


}
