package com.example.movies10;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("author")
    String author;
    @SerializedName("review")
    String reviewText;
    @SerializedName("type")
    String reviewType;

    public Review(String author, String reviewText, String ReviewType) {
        this.author = author;
        this.reviewText = reviewText;
        this.reviewType = ReviewType;
    }

    public String getAuthor() {
        return author;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getReviewType() {
        return reviewType;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author=" + author + '\'' +
                ", reviewText=" + reviewText + '\'' +
                ", reviewType=" + reviewType + '\'' +
                '}';
    }
}
