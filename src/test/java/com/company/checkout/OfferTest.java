package com.company.checkout;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static com.company.checkout.Offer.BUY1_GET1_FREE;
import static com.company.checkout.Offer.BUY3_FOR_THE_PRICE_OF2;

public class OfferTest {

    private Offer offer;

    @Test
    public void shouldReturnDiscountedPrice() {
        //Given
        offer = new Offer(BUY1_GET1_FREE, new BigDecimal("0.5"),
                Product.APPLE, 6);
        BigDecimal expectedResult = new BigDecimal("1.8");

        //When
        BigDecimal actual = offer.computeDiscountedPrice();

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);

    }

    @Test
    public void shouldReturnNonDiscountedPrice() {
        //Given
        offer = new Offer(BUY1_GET1_FREE, new BigDecimal("0.5"),
                Product.APPLE, 1);
        BigDecimal expectedResult = new BigDecimal("0.6");

        //When
        BigDecimal actual = offer.computeDiscountedPrice();

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);

    }

    @Test
    public void shouldReturnNonDiscountedPriceOnBuyThreeForTwo() {
        //Given
        offer = new Offer(BUY3_FOR_THE_PRICE_OF2, new BigDecimal("0.67"),
                Product.ORANGE, 3);
        BigDecimal expectedResult = new BigDecimal("0.5");

        //When
        BigDecimal actual = offer.computeDiscountedPrice();

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnInvalidOffer() {
        //Given
        offer = new Offer("invalid offer", new BigDecimal("0.5"),
                Product.APPLE, 1);
        ;

        //When
        BigDecimal actual = offer.computeDiscountedPrice();

    }

}
