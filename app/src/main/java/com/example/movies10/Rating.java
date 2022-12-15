package com.example.movies10;

import com.google.gson.annotations.SerializedName;

public class Rating {
    @SerializedName("kp")
    private double kp;

    public Rating(float kp) {
        this.kp = kp;
    }

    public double getKp() {
        return kp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kp + '\'' +
                '}';
    }
}
