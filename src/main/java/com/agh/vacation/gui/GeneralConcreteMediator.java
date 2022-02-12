package com.agh.vacation.gui;

import com.agh.vacation.calculator.*;
import com.agh.vacation.calculator.Calculator.CalculatorType;
import com.agh.vacation.fileloading.CriteriaPrioritiesMap;
import com.agh.vacation.fileloading.ExpertDestinationRatings;
import com.agh.vacation.gui.centerpack.CenterPanel;
import com.agh.vacation.gui.centerpack.ComparisonMatrixTabbedPane;
import com.agh.vacation.gui.centerpack.ExpertRatingsTabbedPane;
import com.agh.vacation.gui.eastpack.calculatorgui.CalculatorButton;
import com.agh.vacation.something.Criterion;
import com.agh.vacation.something.InconsistencyResult;
import com.agh.vacation.something.VacationDestination;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.List;

import static com.agh.vacation.calculator.Calculator.InconsistencyType.*;
import static com.agh.vacation.calculator.Calculator.calculateCriteriaScores;
import static com.agh.vacation.something.VacationDestinationComparisonMatricesCreator.createComparisonMatricesBasedOnCriteria;

/**
 * @author Filip Piwosz
 */
class GeneralConcreteMediator implements GeneralMediator {
    private CenterPanel centerPanel;
    private CriteriaPrioritiesMap criteriaPrioritiesMap;
    private ButtonGroup buttonGroup;
    private JList<ExpertDestinationRatings> expertDestinationRatingsJList;
    private List<ExpertDestinationRatings> expertDestinationRatings;
    private List<ComparisonMatricesBasedOnCriteria> multipleExpertMatrices;
    private Result result;
    private InconsistencyResult inconsistencyResult;
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

        this.multipleExpertMatrices = expertDestinationRatings.stream().
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
                multipleExpertMatrices.get(expertIndex)));
        centerPanel.repaint();
        centerPanel.revalidate();
    }

    @Override
    public void saveButtonGroup(ButtonGroup group) {
        this.buttonGroup = group;
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

    @Override
    public void calculateResults() {
        CalculatorType calculatorType = fromButtonGroup();
        ComparisonMatricesBasedOnCriteria finalCompairsonMatrix;


        List<Criterion> criteriaList = criteriaPrioritiesMap.
                keySet().
                stream().
                toList();
        finalCompairsonMatrix = Calculator.calculateAIJ(criteriaList, multipleExpertMatrices);

        List<VacationDestination> destinations = expertDestinationRatings.get(0).ratings;
        VacationCriteriaScoresMap criteriaScoresMap =
                calculateCriteriaScores(destinations, finalCompairsonMatrix, calculatorType);

        //calculate final result - sum(score * criterion priority)
        result = Calculator.calculateResult(criteriaPrioritiesMap, criteriaScoresMap);
        inconsistencyResult = new InconsistencyResult(
                Calculator.calculateInconsistency(SAATY, finalCompairsonMatrix),
                Calculator.calculateInconsistency(CR, finalCompairsonMatrix),
                Calculator.calculateInconsistency(KOCZKODAJ, finalCompairsonMatrix),
                criteriaList);
    }

    @Override
    public void showResults() {
        if (result == null) {
            throw new IllegalStateException("Result was not calculated!");
        }
        centerPanel.removeAll();
        centerPanel.add(new JTextArea(result.display() + "\n" + inconsistencyResult));
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private CalculatorType fromButtonGroup() {
        for (Enumeration<AbstractButton> enumeration = this.buttonGroup.getElements(); enumeration.hasMoreElements(); ) {
            AbstractButton button = enumeration.nextElement();
            if (button.isSelected() && button instanceof CalculatorButton btn) {
                return btn.calculatorType;
            }
        }
        throw new IllegalStateException("No button was found!");
    }
}
