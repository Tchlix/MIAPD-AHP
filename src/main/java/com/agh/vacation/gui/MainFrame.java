package com.agh.vacation.gui;

import com.agh.vacation.gui.centerpack.CenterPanel;
import com.agh.vacation.gui.eastpack.EastPanel;
import com.agh.vacation.gui.southpack.SouthPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        this.setSize(1200, 750);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(new CenterPanel(), BorderLayout.CENTER);
        this.add(new EastPanel(scaledWidth(0.3f), this.getHeight()), BorderLayout.EAST);
        this.add(new SouthPanel(this.getWidth(), scaledHeight(0.15f)), BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private int scaledWidth(float scale) {
        return (int) (this.getWidth() * scale);
    }

    private int scaledHeight(float scale) {
        return (int) (this.getHeight() * scale);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);

    }


}
