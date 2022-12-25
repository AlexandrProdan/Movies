package com.example.movies10;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {
    @SerializedName("docs")
    List<Review> reviewList;

    public ReviewResponse(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviewList=" + reviewList +
                '}';
    }
}
