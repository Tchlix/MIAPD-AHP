package com.agh.vacation;

enum Data {
    CRITERIA(new ComparisonMatrix(Matrix.CRITERIA, IndexMap.CRITERIA)),
    VALUE_FOR_MONEY(new ComparisonMatrix(Matrix.VALUE_FOR_MONEY, IndexMap.CITIES)),
    NIGHT_LIFE(new ComparisonMatrix(Matrix.NIGHT_LIFE, IndexMap.CITIES)),
    SIGHTS(new ComparisonMatrix(Matrix.SIGHTS, IndexMap.CITIES)),
    MUSEUMS(new ComparisonMatrix(Matrix.MUSEUMS, IndexMap.CITIES)),
    FOOD(new ComparisonMatrix(Matrix.FOOD, IndexMap.CITIES));

    final ComparisonMatrix comparisonMatrix;

    Data(ComparisonMatrix comparisonMatrix) {
        this.comparisonMatrix = comparisonMatrix;
    }
}
