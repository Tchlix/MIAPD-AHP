package com.agh.vacation;

import java.util.*;

import static com.agh.vacation.IndexMap.*;

public class Result {
    private final Map<EnumKeys, Double> result = new EnumMap<>(EnumKeys.class);

    Result() {
        clean();
    }

    private void clean() {
        result.clear();
        for (EnumKeys city : CITIES.map.keySet()) {
            result.put(city, 0.);
        }
    }

    void calculate(Map<EnumKeys, Double> criteriaPriorities,
                   Map<EnumKeys, Map<EnumKeys, Double>> criteriaMatricesMap) {
        clean();
        //For each criterion
        for (EnumKeys criterion : CRITERIA.map.keySet()) {
            //We get its priority
            double priority = criteriaPriorities.get(criterion);
            //And matrix with result of every city
            Map<EnumKeys, Double> criteriaValues = criteriaMatricesMap.get(criterion);
            //Then we update result value for each city by priority * cityCriterionValue
            for (EnumKeys city : CITIES.map.keySet()) {
                result.put(city, result.get(city) + priority * criteriaValues.get(city));
            }
        }
    }

    List<Map.Entry<EnumKeys, Double>> getResult() {
        List<Map.Entry<EnumKeys, Double>> list = new ArrayList<>(result.entrySet());
        list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        return list;
    }
}
