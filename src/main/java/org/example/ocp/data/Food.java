package org.example.ocp.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product {

    private final LocalDate bestBefore;

    Food(int id, String name, BigDecimal price) {
        this(id, name, price, LocalDate.now().plusDays(1));
    }

    Food(int id, String name, BigDecimal price, LocalDate bestBefore) {
        super(id, name, price);
        this.bestBefore = bestBefore;
    }

    Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }

    @Override
    public Product applyRating(Rating rating) {
        return new Food(getId(), getName(), getPrice(), rating, getBestBefore());
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public BigDecimal getDiscount() {
        return bestBefore.isEqual(LocalDate.now())
                ? super.getDiscount()
                : BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "bestBefore=" + bestBefore +
                '}';
    }
}
