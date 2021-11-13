package com.agh.vacation;

import java.util.Map;

/**
 * @author Filip Piwosz
 */
class VacationDestination implements PairwiseComparableObject {
    final String name;
    private final Map<Criterion, Integer> ratings;

    VacationDestination(String name, Map<Criterion, Integer> ratings) {
        this.name = name;
        this.ratings = ratings;
    }

    Integer ratingFor(Criterion criterion) {
        return this.ratings.get(criterion);
    }
}
