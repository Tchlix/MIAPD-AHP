package com.agh.vacation;

import java.util.*;

import static com.agh.vacation.IndexMap.*;

public class Result {
    private final Map<Keyword, Double> result = new EnumMap<>(Keyword.class);

    Result() {
        clean();
    }

    private void clean() {
        result.clear();
        for (Keyword city : CITIES.map.keySet()) {
            result.put(city, 0.);
        }
    }

    void calculate(Map<Keyword, Double> criteriaPriorities,
                   Map<Keyword, Map<Keyword, Double>> criteriaMatricesMap) {
        clean();
        //For each criterion
        for (Keyword criterion : CRITERIA.map.keySet()) {
            //We get its priority
            double priority = criteriaPriorities.get(criterion);
            //And matrix with result of every city
            Map<Keyword, Double> criteriaValues = criteriaMatricesMap.get(criterion);
            //Then we update result value for each city by priority * cityCriterionValue
            for (Keyword city : CITIES.map.keySet()) {
                result.put(city, result.get(city) + priority * criteriaValues.get(city));
            }
        }
    }

    List<Map.Entry<Keyword, Double>> getResult() {
        List<Map.Entry<Keyword, Double>> list = new ArrayList<>(result.entrySet());
        list.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
        return list;
    }
}
