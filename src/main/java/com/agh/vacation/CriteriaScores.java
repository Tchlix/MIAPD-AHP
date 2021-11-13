package com.agh.vacation;

import java.util.Map;

/**
 * @author Filip Piwosz
 */
class CriteriaScores {
    final Map<Criterion, Double> scores;

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
