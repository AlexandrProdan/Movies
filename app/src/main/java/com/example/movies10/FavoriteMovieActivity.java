package com.example.movies10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class FavoriteMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFavoriteMovies);
        MoviesAdapter moviesAdapter = new MoviesAdapter();
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        FavoriteMoviesViewModel viewModel = new ViewModelProvider(this).get(FavoriteMoviesViewModel.class);

        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMoviesList(movies);
            }
        });

        moviesAdapter.setOnMovieClickListener(new MoviesAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = MovieDetailedActivity.newIntent(FavoriteMovieActivity.this, movie);
                startActivity(intent);
            }
        });

    }

    public static Intent newIntent(Context context){
        return new Intent(context, FavoriteMovieActivity.class);
    }

}