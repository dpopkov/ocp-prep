package org.example.ocp.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    @Test
    void testComparable() {
        assertTrue(Rating.NOT_RATED.compareTo(Rating.ONE_STAR) < 0);
        assertTrue(Rating.ONE_STAR.compareTo(Rating.TWO_STARS) < 0);
        assertTrue(Rating.TWO_STARS.compareTo(Rating.THREE_STARS) < 0);
        assertTrue(Rating.THREE_STARS.compareTo(Rating.FOUR_STARS) < 0);
        assertTrue(Rating.FOUR_STARS.compareTo(Rating.FIVE_STARS) < 0);
    }
}
