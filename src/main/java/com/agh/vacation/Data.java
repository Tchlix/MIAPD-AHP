package com.agh.vacation;

import java.util.EnumMap;
import java.util.Map;

import static com.agh.vacation.EnumKeys.*;

class Data {
    static final Map<EnumKeys, Integer> criterionIndexMap = new EnumMap<>(Map.of(
            VALUE_FOR_MONEY, 0,
            NIGHT_LIFE, 1,
            SIGHTS, 2,
            MUSEUMS, 3,
            FOOD, 4
    ));

    static final Map<EnumKeys, Integer> cityIndexMap = new EnumMap<>(Map.of(
            VENICE, 0,
            ROME, 1,
            LISBON, 2,
            MADRID, 3,
            WARSAW, 4
    ));

    static final ComparisonMatrix matrixCriterions = new ComparisonMatrix(Matrix.CRITERION, criterionIndexMap);
    static final ComparisonMatrix matrixValueForMoney = new ComparisonMatrix(Matrix.VALUE_FOR_MONEY, cityIndexMap);
    static final ComparisonMatrix matrixNightLife = new ComparisonMatrix(Matrix.NIGHT_LIFE, cityIndexMap);
    static final ComparisonMatrix matrixSights = new ComparisonMatrix(Matrix.SIGHTS, cityIndexMap);
    static final ComparisonMatrix matrixMuseums = new ComparisonMatrix(Matrix.MUSEUMS, cityIndexMap);
    static final ComparisonMatrix matrixFood = new ComparisonMatrix(Matrix.FOOD, cityIndexMap);
}
