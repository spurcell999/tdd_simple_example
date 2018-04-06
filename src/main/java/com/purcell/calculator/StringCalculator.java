package com.purcell.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e026459 on 12/20/2017.
 */
public class StringCalculator {

    public static final int add(final String numbers) {
        String delimiter = ",|n";
        String numbersWithoutDelimiter = numbers;
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("//") + 2;
            delimiter = numbers.substring(delimiterIndex, delimiterIndex + 1);
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf("n") + 1);
        }
        return add(numbersWithoutDelimiter, delimiter);
    }

    private static int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        List negativeNumbers = new ArrayList();
        String[] numbersArray = numbers.split(delimiter);
        for (String number : numbersArray) {
            if (!number.trim().isEmpty()) {
                int numberInt = Integer.parseInt(number.trim());
                if (numberInt < 0) {
                    negativeNumbers.add(numberInt);
                } else if (numberInt <=1000) {
                    returnValue += numberInt;
                }

            }
        }
        if (negativeNumbers.size() > 0) {
                throw new RuntimeException("Negatives not allowed: " + negativeNumbers.toString());
        }
        return returnValue;
    }
}
