package com.example.movies10;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailers {
    @SerializedName("trailers")
    private List<Trailer> trailersList;

    public Trailers(List<Trailer> trailersList) {
        this.trailersList = trailersList;
    }

    public List<Trailer> getTrailersList() {
        return trailersList;
    }

    @Override
    public String toString() {
        return "Trailers{" +
                "trailersList=" + trailersList +
                '}';
    }
}
