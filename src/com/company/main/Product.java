package com.company.main;

import java.math.BigDecimal;

public enum Product {

    APPLE(1, "APPLE", new BigDecimal("0.60")),
    ORANGE(2, "ORANGE", new BigDecimal("0.25"));

    int productId;
    String productName;
    BigDecimal price;

    Product(int productId, String productName, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public static boolean isProductInCatalog(String input) {
        for (Product p : Product.values()) {
            if (p.productName.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public static BigDecimal getPrice(String productName) {
        return Product.valueOf(productName).price;
    }

    @Override
    public String toString() {
        return "Product name is " + productName + " and its unit price is " + String.format("£%.2f", price);
    }

}
