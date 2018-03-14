package com.company.checkout;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class OfferRulesEngineTest {

    @Test
    public void shouldLoadAllOffers() {
        //When
        OfferRulesEngine.loadOfferRules();

        //Then
        assertTrue(!OfferRulesEngine.offerPredicates.isEmpty());
        assertTrue(OfferRulesEngine.offerPredicates.size() == 2);
    }

    @Test
    public void shouldReturnOfferWhenMatchFoundForProduct() {
        //Given
        Map.Entry<String, Integer> productQuantityEntry = new Map.Entry<String, Integer>() {
            @Override
            public String getKey() {
                return "APPLE";
            }

            @Override
            public Integer getValue() {
                return 2;
            }

            @Override
            public Integer setValue(Integer value) {
                return null;
            }
        };
        Offer expectedOffer = new Offer(Offer.BUY1_GET1_FREE, new BigDecimal("0.5"), Product.APPLE, 2);

        //When
        OfferRulesEngine.loadOfferRules();
        Offer actual = OfferRulesEngine.findOffers(productQuantityEntry);

        //Then
        assertNotNull(actual);
        assertTrue(expectedOffer.getProduct().equals(actual.getProduct()));
    }

    @Test
    public void shouldReturnNullWhenNoMatchFoundForProduct() {
        //Given
        Map.Entry<String, Integer> productQuantityEntry = new Map.Entry<String, Integer>() {
            @Override
            public String getKey() {
                return "BANANA";
            }

            @Override
            public Integer getValue() {
                return 2;
            }

            @Override
            public Integer setValue(Integer value) {
                return null;
            }
        };
        Offer expectedOffer = new Offer(Offer.BUY1_GET1_FREE, new BigDecimal("0.5"), Product.APPLE, 2);

        //When
        OfferRulesEngine.loadOfferRules();
        Offer actual = OfferRulesEngine.findOffers(productQuantityEntry);

        //Then
        assertNull(actual);
    }

}
