package com.example.movies10;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("movie?field=rating.kp&search=5-8&sortField=votes.kp&sortType=-1&token=0V2K7QK-SAB4BJY-P9AC76J-64VN8P9&limit=30")
    Single<MovieResponse> loadMovies(@Query("page")int page);

    @GET("movie?token=0V2K7QK-SAB4BJY-P9AC76J-64VN8P9&field=id")
    Single<TrailerResponse>loadTrailers(@Query("search")int id);

    @GET("review?token=0V2K7QK-SAB4BJY-P9AC76J-64VN8P9&field=movieId&limit=4")
    Single<ReviewResponse>loadReview(@Query("search")int id);
}
