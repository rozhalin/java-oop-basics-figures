package com.efimchick.tasks.figures;

import static com.efimchick.tasks.figures.MathConstant.EPSILON;

public class DoubleComparator {
    public static int doubleCompare(Double firstNumber, Double secondNumber) {
        if (Math.abs(firstNumber - secondNumber) < EPSILON) {
            return 0;
        } else if (firstNumber > secondNumber) {
            return 1;
        } else {
            return -1;
        }
    }
}
