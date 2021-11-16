package com.agh.vacation;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.Objects;

@Jacksonized
@Builder
class Criterion implements PairwiseComparableObject {
    private final String name;

    Criterion(String name) {
        this.name = name;
    }

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
