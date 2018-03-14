package com.company.checkout;

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
    public void shouldReturnCheckoutPriceWithExcludingNull() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("APPLE", "APPLE", "ORANGE", null));
        BigDecimal expectedResult = new BigDecimal("0.85");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldReturnCheckoutPriceWithAllNull() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList(null, null));
        BigDecimal expectedResult = new BigDecimal("0.0");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldReturnCheckoutPriceWithEmptyString() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList(" "));
        BigDecimal expectedResult = new BigDecimal("0.0");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldReturnCheckoutPriceWithOfferOnOneProduct() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("APPLE", "APPLE", "ORANGE"));
        BigDecimal expectedResult = new BigDecimal("0.85");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldReturnCheckoutPriceWithOfferOnBothProducts() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("APPLE", "APPLE", "ORANGE", "ORANGE", "ORANGE"));
        BigDecimal expectedResult = new BigDecimal("1.10");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldReturnCheckoutPriceWithOfferAndInvalidProducts() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("APPLE", "APPLE", "sony", "panasonic", "ORANGE"));
        BigDecimal expectedResult = new BigDecimal("0.85");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test
    public void shouldReturnCheckoutPriceWithNoOfferOnAnyProduct() {
        //Given
        ArrayList<String> trolleyItems = new ArrayList<>(Arrays.asList("APPLE", "ORANGE"));
        BigDecimal expectedResult = new BigDecimal("0.85");

        //When
        BigDecimal actual = trolleyPriceCalculator.computeCheckoutPrice(trolleyItems);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
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
