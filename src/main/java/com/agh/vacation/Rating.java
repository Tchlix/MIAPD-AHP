package com.agh.vacation;

import static java.lang.Math.abs;

record Rating(int value) {
    int difference(Rating other) {
        return abs(this.value - other.value);
    }
}
