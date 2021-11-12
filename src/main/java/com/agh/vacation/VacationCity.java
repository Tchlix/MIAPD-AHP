package com.agh.vacation;

import java.util.Map;

class VacationCity {
    String name;
    Map<Criterion, Rating> criterionRatingEnumMap;

    VacationCity(String name, Map<Criterion, Rating> criterionRatingEnumMap) {
        this.name = name;
        this.criterionRatingEnumMap = criterionRatingEnumMap;
    }
}
