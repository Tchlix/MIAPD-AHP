package com.agh.vacation;

enum Data {
    CRITERIA(new ComparisonMatrix(PairwiseMatrix.CRITERIA, IndexMap.CRITERIA)),
    VALUE_FOR_MONEY(new ComparisonMatrix(PairwiseMatrix.VALUE_FOR_MONEY, IndexMap.CITIES)),
    NIGHT_LIFE(new ComparisonMatrix(PairwiseMatrix.NIGHT_LIFE, IndexMap.CITIES)),
    SIGHTS(new ComparisonMatrix(PairwiseMatrix.SIGHTS, IndexMap.CITIES)),
    MUSEUMS(new ComparisonMatrix(PairwiseMatrix.MUSEUMS, IndexMap.CITIES)),
    FOOD(new ComparisonMatrix(PairwiseMatrix.FOOD, IndexMap.CITIES));

    final ComparisonMatrix comparisonMatrix;

    Data(ComparisonMatrix comparisonMatrix) {
        this.comparisonMatrix = comparisonMatrix;
    }
}
