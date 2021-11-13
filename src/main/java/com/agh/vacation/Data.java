package com.agh.vacation;

import java.util.EnumMap;
import java.util.Map;

import static com.agh.vacation.EnumKeys.*;

public class Data {
    final Map<EnumKeys, Integer> criterionIndexMap = new EnumMap<>(EnumKeys.class);
    final Map<EnumKeys, Integer> cityIndexMap = new EnumMap<>(EnumKeys.class);
    final ComparisonMatrix matrixCriterions;
    final ComparisonMatrix matrixValueForMoney;
    final ComparisonMatrix matrixNightLife;
    final ComparisonMatrix matrixSights;
    final ComparisonMatrix matrixMuseums;
    final ComparisonMatrix matrixFood;
    Data() {
        criterionIndexMap.put(VALUE_FOR_MONEY, 0);
        criterionIndexMap.put(NIGHT_LIFE, 1);
        criterionIndexMap.put(SIGHTS, 2);
        criterionIndexMap.put(MUSEUMS, 3);
        criterionIndexMap.put(FOOD, 4);

        cityIndexMap.put(VENICE, 0);
        cityIndexMap.put(ROME, 1);
        cityIndexMap.put(LISBON, 2);
        cityIndexMap.put(MADRID, 3);
        cityIndexMap.put(WARSAW, 4);

        matrixCriterions = new ComparisonMatrix(Matrixes.matrixCriterions, criterionIndexMap);
        matrixValueForMoney = new ComparisonMatrix(Matrixes.matrixValueForMoney, cityIndexMap);
        matrixNightLife = new ComparisonMatrix(Matrixes.matrixNightLife, cityIndexMap);
        matrixSights = new ComparisonMatrix(Matrixes.matrixSights, cityIndexMap);
        matrixMuseums = new ComparisonMatrix(Matrixes.matrixMuseums, cityIndexMap);
        matrixFood = new ComparisonMatrix(Matrixes.matrixFood, cityIndexMap);
    }
}
