package com.agh.vacation;

import java.util.Map;

/**
 * @author Filip Piwosz
 */
class CriteriaScores {
    final Map<Criterion, Double> criteriaScores;

    CriteriaScores(Map<Criterion, Double> criteriaScores) {
        this.criteriaScores = criteriaScores;
    }

    Double scoreFor(Criterion criterion) {
        return this.criteriaScores.get(criterion);
    }

}
