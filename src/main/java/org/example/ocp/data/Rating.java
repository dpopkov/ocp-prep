package org.example.ocp.data;

public enum Rating {
    NOT_RATED(0),
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

    private static final int MAX_STARS = 5;
    private static final String EMPTY = "\u2606";
    private static final String STAR = "\u2605";

    private final int numStars;
    private final String stars;

    Rating(int numStars) {
        this.numStars = numStars;
        this.stars = stars(numStars);
    }

    public int getNumStars() {
        return numStars;
    }

    public String getStars() {
        return stars;
    }

    public static Rating ofNumStars(int numStars) {
        return numStars >= 0 && numStars <= MAX_STARS ? Rating.values()[numStars] : NOT_RATED;
    }

    private static String stars(int n) {
        return STAR.repeat(n) + EMPTY.repeat(MAX_STARS - n);
    }

}
