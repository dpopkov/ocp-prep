package org.example.ocp.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Product implements Rateable<Product> {

    public static final BigDecimal DEFAULT_DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    private final int id;
    private final String name;
    private final BigDecimal price;
    private final  Rating rating;

    public Product(int id, String name, BigDecimal price) {
        this(id, name, price, Rating.NOT_RATED);
    }

    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    @Override
    public abstract Product applyRating(Rating rating);

    @Override
    public Rating getRating() {
        return rating;
    }

    public BigDecimal getDiscount() {
        return price.multiply(DEFAULT_DISCOUNT_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + getDiscount() +
                ", rating=" + rating.getStars() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
