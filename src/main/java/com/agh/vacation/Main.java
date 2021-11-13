package com.agh.vacation;

import java.util.EnumMap;
import java.util.Map;

public class Main {
    private static final int CALCULATOR_PRECISION = 6;
    private static final int RESULT_PRECISION = 2;

    public static void main(String[] args) {
        EigenvalueCalculator calculator = new EigenvalueCalculator();
        Map<EnumKeys, Map<EnumKeys, Double>> criteriaMap = new EnumMap<>(Map.of(
                EnumKeys.VALUE_FOR_MONEY, calculator.calculatePriorities(Data.matrixValueForMoney, CALCULATOR_PRECISION),
                EnumKeys.FOOD, calculator.calculatePriorities(Data.matrixFood, CALCULATOR_PRECISION),
                EnumKeys.MUSEUMS, calculator.calculatePriorities(Data.matrixMuseums, CALCULATOR_PRECISION),
                EnumKeys.NIGHT_LIFE, calculator.calculatePriorities(Data.matrixNightLife, CALCULATOR_PRECISION),
                EnumKeys.SIGHTS, calculator.calculatePriorities(Data.matrixSights, CALCULATOR_PRECISION)
        ));

        Map<EnumKeys, Double> criteriaPriorities = calculator.calculatePriorities(Data.matrixCriteria, CALCULATOR_PRECISION);
        Result result = new Result();
        result.calculate(criteriaPriorities, criteriaMap);
        result.getResult().forEach(s ->
                System.out.printf("%s : %." + RESULT_PRECISION + "f%n", s.getKey(), s.getValue()));
    }
}
