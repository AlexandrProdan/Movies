package com.example.movies10;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("docs")
    List<Movie> movies;

    public MovieResponse(List<Movie> movieList) {
        this.movies = movieList;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movies=" + movies +
                '}';
    }
}
