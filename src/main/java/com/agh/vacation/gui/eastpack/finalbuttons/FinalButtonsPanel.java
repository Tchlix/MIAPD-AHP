package com.agh.vacation.gui.eastpack.finalbuttons;

import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class FinalButtonsPanel extends JPanel {
    public FinalButtonsPanel(int width, int height, GeneralMediator mediator) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.add(new CalculateButton(mediator));
        this.add(new ShowResultsButton(mediator));
    }
}
