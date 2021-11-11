package com.agh.vacation;

import java.util.Map;

class VacationDestination {
    String name;
    Map<Criterion, Rating> criterionRatingEnumMap;

    VacationDestination(String name, Map<Criterion, Rating> criterionRatingEnumMap) {
        this.name = name;
        this.criterionRatingEnumMap = criterionRatingEnumMap;
    }
}
