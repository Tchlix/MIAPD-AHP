package com.agh.vacation;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.EnumMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Criterion, Rating> lisbonMap = new EnumMap<>(Criterion.class);
        lisbonMap.put(Criterion.FOOD, new Rating(4));
        lisbonMap.put(Criterion.NIGHT_LIFE, new Rating(4));
        lisbonMap.put(Criterion.SIGHTS, new Rating(5));
        lisbonMap.put(Criterion.MUSEUMS, new Rating(4));
        lisbonMap.put(Criterion.VALUE_FOR_MONEY, new Rating(4));

        VacationCity lisbon = new VacationCity("Lisbon", lisbonMap);

        Map<Criterion, Rating> munichMap = new EnumMap<Criterion, Rating>(Criterion.class);
        munichMap.put(Criterion.FOOD, new Rating(4));
        munichMap.put(Criterion.NIGHT_LIFE, new Rating(4));
        munichMap.put(Criterion.SIGHTS, new Rating(4));
        munichMap.put(Criterion.MUSEUMS, new Rating(3));
        munichMap.put(Criterion.VALUE_FOR_MONEY, new Rating(4));

        VacationCity munich = new VacationCity("Munich", munichMap);

        RealMatrix matrix = new Array2DRowRealMatrix(new double[][]{
                {1, 3, 5, 7, 9},
                {1. / 3., 1, 3, 5, 7},
                {1. / 5., 1. / 3., 1, 3, 5},
                {1. / 7., 1. / 5., 1. / 3., 1, 3},
                {1. / 9., 1. / 7., 1. / 5., 1. / 3., 1}
        });
        Map<Criterion, Integer> criterionIndexMap = new EnumMap<>(Criterion.class);
        criterionIndexMap.put(Criterion.VALUE_FOR_MONEY, 0);
        criterionIndexMap.put(Criterion.NIGHT_LIFE, 1);
        criterionIndexMap.put(Criterion.SIGHTS, 2);
        criterionIndexMap.put(Criterion.MUSEUMS, 3);
        criterionIndexMap.put(Criterion.FOOD, 4);

        CriteriaComparisonMatrix comparisonMatrix = new CriteriaComparisonMatrix(matrix, criterionIndexMap);

        CriteriaEigenvalueCalculator calculator = new CriteriaEigenvalueCalculator();
        System.out.println(calculator.calculateCriteriaEigenValues(comparisonMatrix));
        System.out.println(calculator.calculateCriteriaEigenValues(comparisonMatrix));
    }
}
