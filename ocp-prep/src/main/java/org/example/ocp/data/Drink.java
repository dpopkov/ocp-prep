package org.example.ocp.data;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Drink extends Product {

    private static final LocalTime START_DISCOUNT = LocalTime.of(16, 30);
    private static final LocalTime END_DISCOUNT = LocalTime.of(17, 30);

    Drink(int id, String name, BigDecimal price) {
        super(id, name, price);
    }

    Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    @Override
    public Product applyRating(Rating rating) {
        return new Drink(getId(), getName(), getPrice(), rating);
    }

    @Override
    public BigDecimal getDiscount() {
        LocalTime now = LocalTime.now();
        return (now.isAfter(START_DISCOUNT) && now.isBefore(END_DISCOUNT))
            ? super.getDiscount()
            : BigDecimal.ZERO;
    }
}
