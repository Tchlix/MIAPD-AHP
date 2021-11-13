package com.agh.vacation;

import java.util.EnumMap;
import java.util.Map;

public class Main {
    private static final int CALCULATOR_PRECISION = 6;
    private static final int RESULT_PRECISION = 2;

    public static void main(String[] args) {
        EigenvalueCalculator calculator = new EigenvalueCalculator();
        Map<EnumKeys, Map<EnumKeys, Double>> criteriaMatricesMap = new EnumMap<>(Map.of(
                EnumKeys.VALUE_FOR_MONEY, calculator.calculatePriorities(Data.VALUE_FOR_MONEY.comparisonMatrix, CALCULATOR_PRECISION),
                EnumKeys.FOOD, calculator.calculatePriorities(Data.FOOD.comparisonMatrix, CALCULATOR_PRECISION),
                EnumKeys.MUSEUMS, calculator.calculatePriorities(Data.MUSEUMS.comparisonMatrix, CALCULATOR_PRECISION),
                EnumKeys.NIGHT_LIFE, calculator.calculatePriorities(Data.NIGHT_LIFE.comparisonMatrix, CALCULATOR_PRECISION),
                EnumKeys.SIGHTS, calculator.calculatePriorities(Data.SIGHTS.comparisonMatrix, CALCULATOR_PRECISION)
        ));

        Map<EnumKeys, Double> criteriaPriorities = calculator.calculatePriorities(Data.CRITERIA.comparisonMatrix, CALCULATOR_PRECISION);
        Result result = new Result();
        result.calculate(criteriaPriorities, criteriaMatricesMap);
        result.getResult().forEach(s ->
                System.out.printf("%s : %." + RESULT_PRECISION + "f%n", s.getKey(), s.getValue()));
    }
}
