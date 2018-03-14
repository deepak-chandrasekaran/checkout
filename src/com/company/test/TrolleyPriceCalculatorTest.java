package com.company.test;

import com.company.main.TrolleyPriceCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TrolleyPriceCalculatorTest {

    private TrolleyPriceCalculator trolleyPriceCalculator;

    @Before
    public void setUp() {
        trolleyPriceCalculator = new TrolleyPriceCalculator();
    }

    @Test
    public void shouldReturnTotalTrolleyPriceWithCatalogProducts() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("APPLE", "APPLE", "ORANGE"));
        BigDecimal expectedResult = new BigDecimal("1.45");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }


    @Test
    public void shouldReturnZeroPriceWithNonCatalogProducts() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("sony", "panasonic"));
        BigDecimal expectedResult = new BigDecimal("0.0");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }


    @Test
    public void shouldReturnZeroTotalPriceWithNoProducts() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Collections.emptyList());
        BigDecimal expectedResult = new BigDecimal("0.0");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldFilterNonCatalogProductsAndComputeTotal() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("SONY", "PANASONIC", "APPLE", "ORANGE", "ORANGE", "@#$%^&*"));
        BigDecimal expectedResult = new BigDecimal("1.1");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldAcceptProductsInAnyCaseToComputeTotal() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("apple", "Orange", "OrAngE"));
        BigDecimal expectedResult = new BigDecimal("1.1");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldReturnZeroTotalPriceWithNullProducts() {
        //Given
        ArrayList<String> trolleyItems = null;
        BigDecimal expectedResult = new BigDecimal("0.0");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

}
