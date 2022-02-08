package com.agh.vacation.something;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents fundamental scale for AHP method
 * Contains a map, that returns proper value from fundamental AHP scale given a difference in rating
 */
class RatingDifferenceMapper {
    static Map<Integer, Double> mapForAHP = new HashMap<>(Map.of(
            0, 1.,
            1, 3.,
            2, 5.,
            3, 7.,
            4, 9.
    ));

    private RatingDifferenceMapper() {
    }
}
