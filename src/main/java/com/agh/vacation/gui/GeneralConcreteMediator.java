package com.agh.vacation.gui;

import com.agh.vacation.ComparisonMatricesBasedOnCriteria;
import com.agh.vacation.CriteriaPrioritiesMap;
import com.agh.vacation.Criterion;
import com.agh.vacation.ExpertDestinationRatings;
import com.agh.vacation.gui.centerpack.CenterPanel;
import com.agh.vacation.gui.centerpack.ComparisonMatrixTabbedPane;
import com.agh.vacation.gui.centerpack.ExpertRatingsTabbedPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.agh.vacation.VacationDestinationComparisonMatricesCreator.createComparisonMatricesBasedOnCriteria;

/**
 * @author Filip Piwosz
 */
class GeneralConcreteMediator implements GeneralMediator {
    private CenterPanel centerPanel;
    private CriteriaPrioritiesMap criteriaPrioritiesMap;
    private ButtonGroup group;
    private JList<ExpertDestinationRatings> expertDestinationRatingsJList;
    private List<ExpertDestinationRatings> expertDestinationRatings;
    private List<ComparisonMatricesBasedOnCriteria> comparisonMatricesBasedOnCriteria;
    private int expertIndex = 0;

    boolean isShowingRatings = true;

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
        centerPanel.repaint();
        centerPanel.add(new JTextArea(criteriaPrioritiesMap.toString()));
        centerPanel.revalidate();
    }

    @Override
    public void saveExpertRatings(List<ExpertDestinationRatings> expertDestinationRatings) {
        this.expertDestinationRatings = expertDestinationRatings;
        this.expertDestinationRatingsJList.setListData(expertDestinationRatings
                .toArray(new ExpertDestinationRatings[0]));
        List<Criterion> criteriaList = criteriaPrioritiesMap.
                keySet().
                stream().
                toList();

        this.comparisonMatricesBasedOnCriteria = expertDestinationRatings.stream().
                map(expert -> createComparisonMatricesBasedOnCriteria(criteriaList, expert.ratings)).toList();
    }

    @Override
    public void showExpertRatings() {
        this.isShowingRatings = true;
        if (this.expertDestinationRatings == null) {
            throw new IllegalStateException("Expert ratings were not loaded!");
        }
        centerPanel.removeAll();
        centerPanel.add(new ExpertRatingsTabbedPane(centerPanel.getPreferredSize(),
                expertDestinationRatings.get(expertIndex)));
        centerPanel.repaint();
        centerPanel.revalidate();
    }

    @Override
    public void setExpertRatingList(JList<ExpertDestinationRatings> expertRatingList) {
        this.expertDestinationRatingsJList = expertRatingList;
    }

    @Override
    public void showExpertMatrices() {
        this.isShowingRatings = false;
        if (this.expertDestinationRatings == null) {
            throw new IllegalStateException("Expert ratings were not loaded!");
        }
        centerPanel.removeAll();
        Dimension dimension = centerPanel.getPreferredSize();
        dimension.setSize((int) (dimension.width * 0.9), (int) (dimension.height * 0.9));
        centerPanel.add(new ComparisonMatrixTabbedPane(dimension,
                comparisonMatricesBasedOnCriteria.get(expertIndex)));
        centerPanel.repaint();
        centerPanel.revalidate();
    }

    @Override
    public void saveButtonGroup(ButtonGroup group) {
        this.group = group;
    }

    @Override
    public void setExpertIndex(int index) {
        this.expertIndex = index;
        if (isShowingRatings) {
            this.showExpertRatings();
        } else {
            this.showExpertMatrices();
        }
    }

}
