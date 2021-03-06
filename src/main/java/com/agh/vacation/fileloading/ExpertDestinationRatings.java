package com.agh.vacation.fileloading;

import com.agh.vacation.ds.VacationDestination;

import java.util.List;

/**
 * @author Filip Piwosz
 */
public class ExpertDestinationRatings {
    public final String expertName;
    public final List<VacationDestination> ratings;

    public ExpertDestinationRatings(String expertName, List<VacationDestination> ratings) {
        this.expertName = expertName;
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return expertName;
    }
}
