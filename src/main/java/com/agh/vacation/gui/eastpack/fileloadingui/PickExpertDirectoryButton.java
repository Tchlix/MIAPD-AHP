package com.agh.vacation.gui.eastpack.fileloadingui;

import com.agh.vacation.fileloading.ExpertDestinationRatings;
import com.agh.vacation.gui.GeneralMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.agh.vacation.fileloading.DestinationLoader.loadMultipleExpertsDestinationRatings;

/**
 * @author Filip Piwosz
 */
class PickExpertDirectoryButton extends JButton implements ActionListener {
    private GeneralMediator mediator;

    PickExpertDirectoryButton(GeneralMediator mediator) {
        super();
        setText("LOAD DIRECTORY WITH EXPERT RATING");
        this.mediator = mediator;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this) {

            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File("."));
            jFileChooser.setDialogTitle("Choose directory with expert ratings");
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = jFileChooser.showOpenDialog(this);

            switch (returnVal) {
                case JFileChooser.CANCEL_OPTION -> System.err.println("No directory has been chosen!");
                case JFileChooser.ERROR_OPTION -> System.err.println("File Chooser Error");
                case JFileChooser.APPROVE_OPTION -> {
                    try {
                        List<ExpertDestinationRatings> multipleExpertsDestinationRatings =
                                loadMultipleExpertsDestinationRatings(jFileChooser.getSelectedFile().toPath());
                        mediator.saveExpertRatings(multipleExpertsDestinationRatings);
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }

            }
        }
    }
}
