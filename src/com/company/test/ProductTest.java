package com.company.test;

import com.company.main.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductTest {

    @Test
    public void shouldReturnFalseIfNotValidProduct() {
        //Given
        String input = "SONY";

        //Then
        assertFalse(Product.isProductInCatalog(input));
    }

    @Test
    public void shouldReturnTrueIfValidProduct() {
        //Given
        String input = "APPLE";

        //Then
        assertTrue(Product.isProductInCatalog(input));
    }

    @Test
    public void shouldReturnPriceIfValidProduct() {
        //Given
        String input = "ORANGE";
        BigDecimal expectedResult = new BigDecimal("0.25");

        //When
        BigDecimal actual = Product.getPrice(input);

        //Then
        Assert.assertTrue(expectedResult.compareTo(actual) == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNotValidProduct() {
        //Given
        String input = "SONY";

        //When
        Product.getPrice(input);
    }
}
