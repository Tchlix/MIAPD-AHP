package com.agh.vacation.gui.eastpack.fileloadingui;

import com.agh.vacation.Logging;
import com.agh.vacation.fileloading.CriteriaJSONLoader;
import com.agh.vacation.fileloading.CriteriaPrioritiesMap;
import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Filip Piwosz
 */
class ChooseCriteriaFileButton extends JButton implements ActionListener {
    private final transient GeneralMediator generalMediator;

    ChooseCriteriaFileButton(GeneralMediator generalMediator) {
        super();
        this.generalMediator = generalMediator;
        setText("CHOOSE CRITERIA");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {
            JFileChooser jFileChooser = new JFileChooser();

            jFileChooser.setFileFilter(new FileNameExtensionFilter("Select json file with criteria", "json"));
            jFileChooser.setCurrentDirectory(new File("."));
            int returnVal = jFileChooser.showOpenDialog(this);

            switch (returnVal) {
                case JFileChooser.CANCEL_OPTION -> Logging.error("No criteria file has been chosen!");
                case JFileChooser.ERROR_OPTION -> Logging.error("File Chooser Error");
                case JFileChooser.APPROVE_OPTION -> loadCriteria(jFileChooser);
                default -> Logging.error("Unknown enum");
            }
        }
    }

    private void loadCriteria(JFileChooser jFileChooser) {
        try {
            CriteriaPrioritiesMap criteriaPrioritiesMap =
                    CriteriaJSONLoader.loadCriteria(jFileChooser.getSelectedFile().toPath());
            generalMediator.saveCriteria(criteriaPrioritiesMap);
        } catch (IOException ex) {
            Logging.error(ex.getMessage());
        }
    }
}
