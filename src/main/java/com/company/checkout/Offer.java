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
        return null;
    }


    public Product getProduct() {
        return product;
    }

}
