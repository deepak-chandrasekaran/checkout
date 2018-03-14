package com.company.checkout;

import java.math.BigDecimal;

public class Offer {

    private String name;
    private BigDecimal percent;

    Offer(String name, BigDecimal percent) {
        this.name = name;
        this.percent = percent;
    }


}
