package com.agh.vacation.gui.eastpack;

import com.agh.vacation.gui.GeneralMediator;
import com.agh.vacation.gui.eastpack.calculatorgui.ChooseCalculatorPanel;
import com.agh.vacation.gui.eastpack.choosecontextpanel.ContextPanel;
import com.agh.vacation.gui.eastpack.fileloadingui.FileLoadingPanel;
import com.agh.vacation.gui.eastpack.finalbuttons.FinalButtonsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class EastPanel extends JPanel {

    public EastPanel(int width, int height, GeneralMediator generalMediator) {
        super();
        this.setBackground(new Color(0xC0FFEE));
        this.setPreferredSize(new Dimension(width, height));
        this.add(new FileLoadingPanel(width, (int) (height * 0.20f), generalMediator));
        this.add(new ContextPanel(width, (int) (height * 0.20f), generalMediator));
        this.add(new ChooseCalculatorPanel(width, (int) (height * 0.20f), generalMediator));
        this.add(new FinalButtonsPanel(width, (int) (height * 0.20f)));
        setVisible(true);

    }
}
