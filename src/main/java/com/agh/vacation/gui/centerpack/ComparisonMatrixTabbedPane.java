package com.agh.vacation.gui.centerpack;

import com.agh.vacation.ComparisonMatricesBasedOnCriteria;

import javax.swing.*;
import java.awt.*;

/**
 * @author Filip Piwosz
 */
public class ComparisonMatrixTabbedPane extends JTabbedPane {

    public ComparisonMatrixTabbedPane(Dimension preferredSize,
                                      ComparisonMatricesBasedOnCriteria comparisonMatricesBasedOnCriteria) {
        this.setPreferredSize(preferredSize);
        comparisonMatricesBasedOnCriteria.stream()
                .forEach(entry ->
                        addTab(entry.getKey().toString(), new ComparisonMatrixPanel(preferredSize, entry.getValue())));
    }
}
