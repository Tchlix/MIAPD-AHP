package com.agh.vacation;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

import static java.lang.System.lineSeparator;

/**
 *
 */
@Jacksonized
@Builder
public class VacationDestination implements PairwiseComparableObject {
    public final String name;
    public final Map<Criterion, Integer> ratings;


    Integer ratingFor(Criterion criterion) {
        return this.ratings.get(criterion);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ratings.forEach(((criterion, integer) -> builder
                .append(criterion)
                .append(" : ")
                .append(integer)
                .append(lineSeparator())));
        if (!ratings.isEmpty()) {
            builder.delete(builder.length() - 1, builder.length());
        }
        return builder.toString();
    }
}
