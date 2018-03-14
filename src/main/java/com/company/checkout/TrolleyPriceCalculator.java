package com.company.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;

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
    public BigDecimal computeCheckoutPrice(ArrayList<String> tillInput) {
        BigDecimal result = new BigDecimal("0.0");
        if (tillInput != null && !tillInput.isEmpty()) {
            return tillInput.stream()
                    .map(String::toUpperCase)
                    .filter(Product::isProductInCatalog)
                    .map(Product::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return result;
    }

}
