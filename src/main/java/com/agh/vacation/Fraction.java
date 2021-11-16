package com.agh.vacation;

import lombok.extern.jackson.Jacksonized;

import static java.lang.Double.parseDouble;

/**
 * Helper class for loading floating point types from .json
 * @author Filip Piwosz
 */
@Jacksonized
class Fraction {
    final double value;

    Fraction(String valueString) {
        this.value = parseStringParameter(valueString);
    }

    private double parseStringParameter(String valueString) {
        double result;
        if (valueString.contains("/")) {
            String[] splitDoubles = valueString.split("/");
            double nominator = parseDouble(splitDoubles[0]);
            double denominator = parseDouble(splitDoubles[1]);
            result = nominator / denominator;
        } else {
            result = parseDouble(valueString);
        }
        return result;

    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
