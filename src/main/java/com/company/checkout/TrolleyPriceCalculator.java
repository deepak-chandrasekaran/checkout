package com.company.checkout;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/*
   Service to compute the trolley price on checkout
 */
public class TrolleyPriceCalculator {

    /**
     * Computes the total price of the trolley
     *
     * @param tillInput products added at till
     * @return trolley price at checkout
     */
    public BigDecimal computeCheckoutPrice(List<String> tillInput) {
        OfferRulesEngine.loadOfferRules();

        BigDecimal checkoutPrice = new BigDecimal("0.0");
        if (tillInput != null && !tillInput.isEmpty()) {
            //filter the input
            tillInput = filterTillInput(tillInput);

            //builds trolley
            Map<String, Integer> trolley = buildTrolley(tillInput);

            //get offers applicable
            List<Offer> offers = getOffersApplicable(trolley);

        }
        return checkoutPrice;
    }

    /**
     * Gets the list of offers applicable against the offers engine rules
     *
     * @param trolley products with quantity
     * @return offers applicable
     */
    private List<Offer> getOffersApplicable(Map<String, Integer> trolley) {
        return trolley.entrySet().stream()
                .map(OfferRulesEngine::findOffers)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Filters the input to catalog products
     *
     * @param tillInput products scanned at till
     * @return list of products available in catalog
     */
    private List<String> filterTillInput(List<String> tillInput) {
        tillInput.removeIf(Objects::isNull);
        return tillInput.stream()
                .map(String::toUpperCase)
                .filter(Product::isProductInCatalog)
                .collect(Collectors.toList());
    }

    /**
     * Builds a trolley processing the till input
     *
     * @param products list of products
     * @return trolley
     */
    private Map<String, Integer> buildTrolley(List<String> products) {
        return products.stream()
                .collect(Collectors.groupingBy(it -> it))
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().size())
                );
    }

}
