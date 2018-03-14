package com.company.checkout;

import java.math.BigDecimal;

public class Offer {

    static final String BUY1_GET1_FREE = "Buy 1, get one free";
    static final String BUY3_FOR_THE_PRICE_OF2 = "3 for the price of 2";

    private String name;
    private BigDecimal percent;
    private Product product;
    private Integer totalQuantity;

    Offer(String name, BigDecimal percent, Product product, Integer totalQuantity) {
        this.name = name;
        this.percent = percent;
        this.product = product;
        this.totalQuantity = totalQuantity;
    }


    BigDecimal computeDiscountedPrice() {
        int reminder;
        switch (name) {
            case BUY1_GET1_FREE:
                reminder = totalQuantity % 2;
                return getDiscountedPrice(reminder);
            case BUY3_FOR_THE_PRICE_OF2:
                reminder = totalQuantity % 3;
                return getDiscountedPrice(reminder);
            default:
                throw new IllegalArgumentException("Invalid offer code");
        }
    }

    private BigDecimal getDiscountedPrice(int reminder) {
        BigDecimal discountedPrice;
        discountedPrice = (product.price).multiply(percent);
        if (reminder == 0) {
            return new BigDecimal(totalQuantity).multiply(discountedPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            return new BigDecimal(totalQuantity - reminder)
                    .multiply(discountedPrice).add((product.price)
                            .multiply(new BigDecimal(reminder))).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    Product getProduct() {
        return product;
    }

}
