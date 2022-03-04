package com.agh.vacation.ds;

import java.util.Objects;

public record Criterion(String name) implements PairwiseComparableObject {

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criterion criterion = (Criterion) o;
        return Objects.equals(name, criterion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
