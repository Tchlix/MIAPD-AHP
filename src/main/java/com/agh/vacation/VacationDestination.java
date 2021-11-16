package com.agh.vacation;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 *
 */
@Jacksonized
@Builder
class VacationDestination implements PairwiseComparableObject {
    final String name;
    private final Map<Criterion, Integer> ratings;


    Integer ratingFor(Criterion criterion) {
        return this.ratings.get(criterion);
    }

    @Override
    public String toString() {
        return name;
    }
}
