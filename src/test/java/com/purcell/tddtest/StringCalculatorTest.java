package com.purcell.tddtest;

import com.purcell.calculator.StringCalculator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by e026459 on 12/20/2017.
 */
public class StringCalculatorTest {

    @Test
    public final void whenSingleValuePassedToAdd() {
        StringCalculator.add("1");
        Assert.assertTrue(true);
    }

    @Test
    public final void when2NumbersAreUsedThenNoExceptionIsThrown() {
        StringCalculator.add("1,2");
        Assert.assertTrue(true);
    }

    @Test(expected=RuntimeException.class)
    public final void whenNonNumberIsUsedThenExceptionIsThrown() {
        StringCalculator.add("1,X");
    }

    @Test
    public void whenEmptyStringIsUsedThenReturnValueIs0() {
        Assert.assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public final void whenOneNumberIsUsedThenReturnValueIsThatSameNumber() {
        Assert.assertEquals(3, StringCalculator.add("3"));
    }

    @Test
    public final void whenTwoNumbersAreUsedThenReturnValueIsTheirSum() {
        Assert.assertEquals(3+6, StringCalculator.add("3,6"));
    }

    @Test
    public final void whenAnyNumberOfNumbersIsUsedThenReturnValuesAreTheirSums() {
        Assert.assertEquals(3+6+15+18+46+33, StringCalculator.add("3,6,15,18,46,33"));
    }

    @Test
    public final void whenNewLineIsUsedBetweenNumbersThenReturnValuesAreTheirSum() {
        Assert.assertEquals(3+6+15, StringCalculator.add("3,6n15"));
    }

    @Test
    public final void whenDelimiterIsSpecifiedThenItIsUsedToSeparateNumbers() {
        Assert.assertEquals(3+6+15, StringCalculator.add("//;n3;6;15"));
    }

    @Test(expected = RuntimeException.class)
    public final void whenNegativeNumberIsusedThenRuntimeExceptionIsThrown() {
        StringCalculator.add("3, -6,15,18,46, 33");
    }

    @Test
    public final void whenNegativeNumbersAreusedThenRuntimeExceptionIsThrown() {
        RuntimeException exception = null;
        try {
            StringCalculator.add("3, -6, 15, -18, 46, 33");
        } catch (RuntimeException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        Assert.assertEquals("Negatives not allowed: [-6, -18]", exception.getMessage());
    }

    @Test
    public final void whenOneOrMoreNumbersAreGreaterThan1000IsUsedThenItIsNotIncludedInSum() {
        Assert.assertEquals(3+1000+6, StringCalculator.add("3,1000,10001,6,1234"));
    }
}
