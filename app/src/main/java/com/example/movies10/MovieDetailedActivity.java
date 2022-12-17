package com.example.movies10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailedActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "movie";
    public static final String TAG = "MovieDetailedActivity";

    ScrollView scrollView;
    LinearLayout linearLayout;
    ImageView imageViewPoster;
    TextView textViewTitle;
    TextView textViewYear;
    TextView textViewDescription;
    MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed);
        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);

        movieDetailViewModel.loadTrailer(movie.getId());
        movieDetailViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                Log.d(TAG, trailers.toString());
            }
        });
    }
//==================================================================================================
    private void initViews(){
        imageViewPoster = findViewById(R.id.ImageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
    }

    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailedActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

}
