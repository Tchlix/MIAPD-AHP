package com.agh.vacation.gui.eastpack.fileloadingui;

import com.agh.vacation.CriteriaPrioritiesMap;
import com.agh.vacation.fileloading.CriteriaJSONLoader;
import com.agh.vacation.gui.CenterPanelMediator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Filip Piwosz
 */
class ChooseCriteriaFileButton extends JButton implements ActionListener {
    private CenterPanelMediator centerPanelMediator;

    ChooseCriteriaFileButton(CenterPanelMediator centerPanelMediator) {
        super();
        this.centerPanelMediator = centerPanelMediator;
        setText("CHOOSE CRITERIA");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            JFileChooser jFileChooser = new JFileChooser();

            jFileChooser.setFileFilter(new FileNameExtensionFilter("Select json file with criteria", "json"));
            int returnVal = jFileChooser.showOpenDialog(this);

            switch (returnVal) {
                case JFileChooser.CANCEL_OPTION -> System.err.println("No criteria file has been chosen!");
                case JFileChooser.ERROR_OPTION -> System.err.println("File Chooser Error");
                case JFileChooser.APPROVE_OPTION -> {
                    try {
                        CriteriaPrioritiesMap criteriaPrioritiesMap =
                                CriteriaJSONLoader.loadCriteria(jFileChooser.getSelectedFile().toPath());
                        centerPanelMediator.showCriteria(criteriaPrioritiesMap);
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        }
    }
}
