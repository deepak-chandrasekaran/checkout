package com.company.checkout;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
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

            //map offer by product
            Map<String, Offer> offersByProduct = offers.stream()
                    .collect(Collectors.toMap(offer -> offer.getProduct()
                            .getProductName(), Function.identity()));

            //compute the checkout price
            checkoutPrice = computeCheckoutPrice(trolley, offersByProduct);
        }
        System.out.println("Final trolley price is: £" + checkoutPrice);
        return checkoutPrice;
    }

    /**
     * Computes the final checkout price of the trolley incl. offers applicable
     *
     * @param trolley         products with quantity
     * @param offersByProduct offers applicable for product
     * @return trolley price
     */
    private BigDecimal computeCheckoutPrice(Map<String, Integer> trolley, Map<String, Offer> offersByProduct) {
        BigDecimal trolleyPrice;
        trolleyPrice = trolley.keySet().stream()
                .map(name -> Optional.ofNullable(offersByProduct.get(name))
                        .map(Offer::computeDiscountedPrice)
                        .orElseGet(() -> (Product.valueOf(name).getPrice()).multiply(BigDecimal.valueOf(trolley.get(name)))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        trolleyPrice = trolleyPrice.setScale(2, BigDecimal.ROUND_HALF_UP);

        return trolleyPrice;
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
