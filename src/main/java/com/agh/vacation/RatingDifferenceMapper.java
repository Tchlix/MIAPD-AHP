package com.agh.vacation;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents fundamental scale for AHP method
 * Contains a map, that returns proper value from fundamental AHP scale given a difference in rating
 *
 * @author Filip Piwosz
 */
class RatingDifferenceMapper {
    private RatingDifferenceMapper() {
    }

    static Map<Integer, Double> mapForAHP() {
        Map<Integer, Double> map = new HashMap<>();
        map.put(0, 1.);
        map.put(1, 3.);
        map.put(2, 5.);
        map.put(3, 7.);
        map.put(4, 9.);
        return map;
    }
}
