package com.agh.vacation.gui;

import com.agh.vacation.fileloading.CriteriaPrioritiesMap;
import com.agh.vacation.fileloading.ExpertDestinationRatings;

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

    void showExpertMatrices();

    void saveButtonGroup(ButtonGroup buttonGroup);

    void setExpertIndex(int index);

    void calculateResults();

    void showResults();
}
