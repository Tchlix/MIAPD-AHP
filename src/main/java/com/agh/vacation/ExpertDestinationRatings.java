package com.agh.vacation;

import java.util.List;

/**
 * @author Filip Piwosz
 */
public class ExpertDestinationRatings {
    public final List<VacationDestination> ratings;

    public ExpertDestinationRatings(List<VacationDestination> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return "expert";
    }
}
