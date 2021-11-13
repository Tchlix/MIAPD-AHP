package com.agh.vacation;

import java.util.*;

public class Result {
    private final Map<EnumKeys, Double> result = new EnumMap<>(EnumKeys.class);

    Result() {
        clean();
    }

    private void clean() {
        result.clear();
        for (EnumKeys city : Data.cityIndexMap.keySet()) {
            result.put(city, 0.);
        }
    }

    void calculate(Map<EnumKeys, Double> criteriaPriorities,
                   Map<EnumKeys, Map<EnumKeys, Double>> criteriaMap) {
        clean();
        for (EnumKeys criterion : Data.criterionIndexMap.keySet()) {
            double priority = criteriaPriorities.get(criterion);
            Map<EnumKeys, Double> criteriaValues = criteriaMap.get(criterion);

            for (EnumKeys city : Data.cityIndexMap.keySet()) {
                result.put(city, result.get(city) + priority * criteriaValues.get(city));
            }
        }
    }

    List<Map.Entry<EnumKeys,Double>> getResult() {
        List<Map.Entry<EnumKeys, Double>> list = new ArrayList<>(result.entrySet());
        list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        return list;
    }
}
