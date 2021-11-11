package com.agh.vacation;

import java.util.EnumMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Criterion, Rating> map = new EnumMap<>(Criterion.class);
        map.put(Criterion.FOOD, new Rating(4));
        map.put(Criterion.NIGHT_LIFE, new Rating(4));
        map.put(Criterion.SIGHTS, new Rating(5));
        map.put(Criterion.MUSEUMS, new Rating(4));
        map.put(Criterion.VALUE_FOR_MONEY, new Rating(4));

        VacationDestination lisbon = new VacationDestination("Lisbon", map);

    }
}
