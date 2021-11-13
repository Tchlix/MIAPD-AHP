package com.agh.vacation;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        EigenvalueCalculator calculator = new EigenvalueCalculator();
        Map<EnumKeys, Double> criteriaPriorities = calculator.calculateCriteriaPriorities(data.matrixCriterions, 3);
        Map<EnumKeys, Double> valueForMoney = calculator.calculateCriteriaPriorities(data.matrixValueForMoney, 3);
        Map<EnumKeys, Double> matrixFood = calculator.calculateCriteriaPriorities(data.matrixFood, 3);
        Map<EnumKeys, Double> matrixMuseums = calculator.calculateCriteriaPriorities(data.matrixMuseums, 3);
        Map<EnumKeys, Double> matrixNightLife = calculator.calculateCriteriaPriorities(data.matrixNightLife, 3);
        Map<EnumKeys, Double> matrixSights = calculator.calculateCriteriaPriorities(data.matrixSights, 3);
    }
}
