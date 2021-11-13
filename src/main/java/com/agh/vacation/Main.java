package com.agh.vacation;

import java.util.EnumMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        EigenvalueCalculator calculator = new EigenvalueCalculator();
        Map<EnumKeys, Map<EnumKeys, Double>> criteriaMap = new EnumMap<>(EnumKeys.class);
        criteriaMap.put(EnumKeys.VALUE_FOR_MONEY, calculator.calculatePriorities(data.matrixValueForMoney, 3));
        criteriaMap.put(EnumKeys.FOOD, calculator.calculatePriorities(data.matrixFood, 3));
        criteriaMap.put(EnumKeys.MUSEUMS, calculator.calculatePriorities(data.matrixMuseums, 3));
        criteriaMap.put(EnumKeys.NIGHT_LIFE, calculator.calculatePriorities(data.matrixNightLife, 3));
        criteriaMap.put(EnumKeys.SIGHTS, calculator.calculatePriorities(data.matrixSights, 3));

        Map<EnumKeys, Double> result = new EnumMap<>(EnumKeys.class);
        for (EnumKeys city : data.cityIndexMap.keySet()) {
            result.put(city, 0.);
        }

        Map<EnumKeys, Double> criteriaPriorities = calculator.calculatePriorities(data.matrixCriterions, 3);

        for (EnumKeys criterion : data.criterionIndexMap.keySet()){
            double priority = criteriaPriorities.get(criterion);
            Map<EnumKeys, Double> criteriumValues = criteriaMap.get(criterion);
            for(EnumKeys city: data.cityIndexMap.keySet()){
                result.put(city,result.get(city)+priority*criteriumValues.get(city));
            }
        }
        System.out.println(result);
    }
}
