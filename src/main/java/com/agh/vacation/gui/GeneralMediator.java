package com.agh.vacation.gui;

import com.agh.vacation.CalculatorType;
import com.agh.vacation.ComparisonMatricesBasedOnCriteria;
import com.agh.vacation.CriteriaPrioritiesMap;
import com.agh.vacation.ExpertDestinationRatings;

import javax.swing.*;
import java.util.List;

/**
 * @author Filip Piwosz
 */
public interface GeneralMediator {
    void saveCriteria(CriteriaPrioritiesMap criteriaPrioritiesMap);

    void showCriteria();

    void saveExpertRatings(List<ExpertDestinationRatings> expertDestinationRatings);

    void showExpertRatings();

    void setExpertRatingList(JList<ExpertDestinationRatings> expertRatingList);

    void saveMatrices(List<ComparisonMatricesBasedOnCriteria> expertRatings);

    void showExpertMatrices();

    void saveButtonGroup(ButtonGroup buttonGroup);

    void setExpertIndex(int index);
}
