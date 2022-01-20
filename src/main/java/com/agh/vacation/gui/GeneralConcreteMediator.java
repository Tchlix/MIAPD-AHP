package com.agh.vacation.gui;

import com.agh.vacation.ComparisonMatricesBasedOnCriteria;
import com.agh.vacation.CriteriaPrioritiesMap;
import com.agh.vacation.ExpertDestinationRatings;
import com.agh.vacation.gui.centerpack.CenterPanel;

import javax.swing.*;
import java.util.List;

/**
 * @author Filip Piwosz
 */
class GeneralConcreteMediator implements GeneralMediator {
    private CenterPanel centerPanel;
    private CriteriaPrioritiesMap criteriaPrioritiesMap;
    private ButtonGroup group;
    private List<ExpertDestinationRatings> expertDestinationRatings;
    private int expertIndex = 0;

    GeneralConcreteMediator(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    @Override
    public void saveCriteria(CriteriaPrioritiesMap criteriaPrioritiesMap) {
        this.criteriaPrioritiesMap = criteriaPrioritiesMap;
    }

    @Override
    public void showCriteria() {
        if (this.criteriaPrioritiesMap == null) {
            throw new IllegalStateException("Criteria were not loaded!");
        }
        centerPanel.removeAll();
        centerPanel.add(new JTextArea(criteriaPrioritiesMap.toString()));
        centerPanel.revalidate();
    }

    @Override
    public void saveExpertRatings(List<ExpertDestinationRatings> expertDestinationRatings) {
        this.expertDestinationRatings = expertDestinationRatings;
    }

    @Override
    public void showExpertRatings() {
        if (this.expertDestinationRatings == null) {
            throw new IllegalStateException("Expert ratings were not loaded!");
        }
        centerPanel.removeAll();
        centerPanel.add(new JTextArea(expertDestinationRatings.toString()));
        centerPanel.revalidate();
    }

    @Override
    public void saveMatrices(List<ComparisonMatricesBasedOnCriteria> expertRatings) {

    }

    @Override
    public void showExpertMatrices() {

    }

    @Override
    public void saveButtonGroup(ButtonGroup group) {
        this.group = group;
    }

    @Override
    public void setExpertIndex(int index) {
        this.expertIndex = index;
        System.out.println(index);
    }

}
