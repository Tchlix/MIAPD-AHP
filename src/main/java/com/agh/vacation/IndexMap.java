package com.agh.vacation;

import java.util.EnumMap;
import java.util.Map;

import static com.agh.vacation.Keyword.*;

enum IndexMap {
    CRITERIA(new EnumMap<>(Map.of(
            VALUE_FOR_MONEY, 0,
            NIGHT_LIFE, 1,
            SIGHTS, 2,
            MUSEUMS, 3,
            FOOD, 4
    ))),
    CITIES(new EnumMap<>(Map.of(
            VENICE, 0,
            ROME, 1,
            LISBON, 2,
            MADRID, 3,
            WARSAW, 4
    )));
    final Map<Keyword, Integer> map;

    IndexMap(Map<Keyword, Integer> map) {
        this.map = map;
    }
}
