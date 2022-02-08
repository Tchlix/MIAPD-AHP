package com.agh.vacation.something;

import java.util.Map;

/**
 * Represents a map of scores gained by VacationDestination for each criterion
 */
public class CriteriaScores {
    private final Map<Criterion, Double> scores;

    public CriteriaScores(Map<Criterion, Double> scores) {
        this.scores = scores;
    }

    public Double scoreFor(Criterion criterion) {
        return this.scores.get(criterion);
    }

    public void putScoreFor(Criterion criterion, Double score) {
        this.scores.put(criterion, score);
    }

}
