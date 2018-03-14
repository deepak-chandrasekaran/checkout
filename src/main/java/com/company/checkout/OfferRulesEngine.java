package com.company.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static com.company.checkout.Offer.BUY1_GET1_FREE;
import static com.company.checkout.Offer.BUY3_FOR_THE_PRICE_OF2;


public final class OfferRulesEngine {
    private static final String APPLE = "APPLE";
    private static final String ORANGE = "ORANGE";
    private static final String BUY1_GET1_FREE_UNIT_PRICE = "0.5";
    private static final String BUY3_FOR_THE_PRICE_OF2_UNIT_PRICE = "0.67";

    static final Map<Predicate<Map.Entry>, BiFunction<Integer, Map.Entry, Offer>> offerPredicates = new HashMap<>();

    static void loadOfferRules() {
        offerPredicates.put((Map.Entry entry) -> {
                    return entry.getKey().equals(APPLE) &&
                            (Integer) entry.getValue() >= 2;
                },
                (integer, entry) -> new Offer(BUY1_GET1_FREE, new BigDecimal(BUY1_GET1_FREE_UNIT_PRICE), Product.APPLE, integer));
        offerPredicates.put((Map.Entry entry) -> {
                    return entry.getKey().equals(ORANGE) &&
                            (Integer) entry.getValue() >= 3;
                },
                (integer, entry) -> new Offer(BUY3_FOR_THE_PRICE_OF2, new BigDecimal(BUY3_FOR_THE_PRICE_OF2_UNIT_PRICE), Product.ORANGE, integer));

    }

    public static Offer findOffers(Map.Entry<String, Integer> productQuantityEntry) {
        Map.Entry<Predicate<Map.Entry>, BiFunction<Integer, Map.Entry, Offer>> matchedOffer
                = offerPredicates.entrySet().stream()
                .filter(e -> e.getKey().test(productQuantityEntry))
                .findFirst()
                .orElse(null);

        if (matchedOffer != null) {
            return matchedOffer.getValue().apply(productQuantityEntry.getValue(), productQuantityEntry);
        }
        return null;
    }
}
