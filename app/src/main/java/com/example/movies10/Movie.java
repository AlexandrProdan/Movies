package com.example.movies10;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("year")
    private int year;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public Rating getRating() {
        return rating;
    }

    public Poster getPoster() {
        return poster;
    }

    @SerializedName("poster")
    private Poster poster;
    @SerializedName("rating")
    private Rating rating;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
