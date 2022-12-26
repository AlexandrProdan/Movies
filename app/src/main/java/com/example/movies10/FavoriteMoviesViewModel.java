package com.example.movies10;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoriteMoviesViewModel extends AndroidViewModel {
    public final MovieDAO movieDAO;

    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDAO = MovieDatabase.getInstance(application).movieDAO();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieDAO.getAllFavoriteMovies();
    }
}
