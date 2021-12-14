package com.agh.vacation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Criterion implements PairwiseComparableObject {
    private final String name;

    List<Criterion> subCriteria;

    public Criterion(String name, List<Criterion> subCriteria) {
        this.name = name;
        this.subCriteria = subCriteria;
    }

    public Criterion(String name) {
        this(name, new ArrayList<>());
    }

    public boolean hasSubCriteria() {
        return this.subCriteria.isEmpty();
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
