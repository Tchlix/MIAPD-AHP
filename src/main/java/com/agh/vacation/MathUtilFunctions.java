package com.agh.vacation;

import static java.lang.Math.floor;
import static java.lang.Math.pow;

/**
 * @author Filip Piwosz
 */
class MathUtilFunctions {
    static double truncateDouble(double value, int truncation) {
        double tenToPower = pow(10, truncation);
        return floor(value * tenToPower) / tenToPower;
    }

    static int maxElementIndex(double[] arr) {
        if (!arrayIsProper(arr)) {
            throw new IllegalArgumentException();
        }
        int index = 0;
        double maxVal = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                index = i;
            }
        }
        return index;
    }

    private static boolean arrayIsProper(double[] arr) {
        boolean result = arr != null && arr.length != 0;
        return result;
    }
}
