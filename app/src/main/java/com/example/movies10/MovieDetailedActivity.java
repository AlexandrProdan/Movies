package com.example.movies10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailedActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "movie";

    ScrollView scrollView;
    LinearLayout linearLayout;
    ImageView imageViewPoster;
    TextView textViewTitle;
    TextView textViewYear;
    TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed);
        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);

        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());
    }

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
