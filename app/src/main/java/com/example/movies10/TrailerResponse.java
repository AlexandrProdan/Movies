package com.example.movies10;

import com.google.gson.annotations.SerializedName;

public class TrailerResponse {
    @SerializedName("videos")
    private Trailers trailers;

    public TrailerResponse(Trailers trailers) {
        this.trailers = trailers;
    }

    public Trailers getTrailers() {
        return trailers;
    }

    @Override
    public String toString() {
        return "TrailerResponse{" +
                "trailers=" + trailers +
                '}';
    }
}
