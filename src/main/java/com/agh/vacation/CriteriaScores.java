package com.agh.vacation;

import java.util.Map;

/**
 * Represents a map of scores gained by VacationDestination for each criterion
 *
 */
class CriteriaScores {
    private final Map<Criterion, Double> scores;

    CriteriaScores(Map<Criterion, Double> scores) {
        this.scores = scores;
    }

    Double scoreFor(Criterion criterion) {
        return this.scores.get(criterion);
    }

    void putScoreFor(Criterion criterion, Double score) {
        this.scores.put(criterion, score);
    }

}
