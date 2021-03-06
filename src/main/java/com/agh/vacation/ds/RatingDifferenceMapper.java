package com.agh.vacation.ds;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents fundamental scale for AHP method
 * Contains a map, that returns proper value from fundamental AHP scale given a difference in rating
 */
class RatingDifferenceMapper {
    private static final Map<Integer, Double> mapForAHP = new HashMap<>(Map.of(
            0, 1.,
            1, 3.,
            2, 5.,
            3, 7.,
            4, 9.
    ));

    private RatingDifferenceMapper() {
    }

    static Double getDifference(Integer integer) {
        return mapForAHP.get(integer);
    }
}
