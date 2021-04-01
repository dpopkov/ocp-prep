package org.example.ocp.data;

@FunctionalInterface
public interface Rateable<T> {

    Rating DEFAULT_RATING = Rating.NOT_RATED;

    T applyRating(Rating rating);

    default T applyRating(int numStars) {
        return applyRating(convert(numStars));
    }

    default Rating getRating() {
        return DEFAULT_RATING;
    }

    static Rating convert(int numStars) {
        Rating[] ratings = Rating.values();
        return numStars >= 0 && numStars < ratings.length
                ? ratings[numStars]
                : DEFAULT_RATING;
    }
}
