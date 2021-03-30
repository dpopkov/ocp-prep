package org.example.ocp.data;

public class Review implements Comparable<Review> {

    private final Rating rating;
    private final String comments;

    public Review(Rating rating, String comments) {
        this.rating = rating;
        this.comments = comments;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating.getStars() +
                ", comments='" + comments + '\'' +
                '}';
    }

    @Override
    public int compareTo(Review other) {
        return other.rating.compareTo(this.rating);
    }
}
