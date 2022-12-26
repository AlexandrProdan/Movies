package com.example.movies10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    ImageView imageViewPoster;
    ImageView imageViewStar;

    Drawable starOn;
    Drawable starOff;

    TextView textViewTitle;
    TextView textViewYear;
    TextView textViewDescription;

    MovieDetailViewModel movieDetailViewModel;

    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;

    RecyclerView recyclerViewTrailer;
    RecyclerView recyclerViewReview;

    MovieDAO movieDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed);

        movieDetailViewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        initViews();
        initDrawables();

        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailer.setAdapter(trailerAdapter);
        trailerAdapter.setOnTrailerClickListener(new TrailerAdapter.OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });


        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        reviewAdapter = new ReviewAdapter();
        recyclerViewReview.setAdapter(reviewAdapter);

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);

        movieDetailViewModel.loadTrailer(movie.getId());
        movieDetailViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailerAdapter.setTrailers(trailers);
                Log.d(TAG, trailers.toString());
            }
        });

        movieDetailViewModel.loadReview(movie.getId());
        movieDetailViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.setReviewList(reviews);
            }
        });

        movieDetailViewModel.getFavoriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDb) {
                if(movieFromDb == null){
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            movieDetailViewModel.insertMovie(movie);
                        }
                    });
                }else{
                    imageViewStar.setImageDrawable(starOn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            movieDetailViewModel.removeMovie(movie.getId());
                        }
                    });
                }
            }
        });

    }
//==================================================================================================
    private void initViews(){
        imageViewPoster = findViewById(R.id.ImageViewPoster);
        imageViewStar = findViewById(R.id.imageViewStar);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailer= findViewById(R.id.recyclerViewTrailers);
        recyclerViewReview = findViewById(R.id.recyclerViewReview);
    }

    private void initDrawables(){
        starOn = ContextCompat.getDrawable(MovieDetailedActivity.this, android.R.drawable.star_big_on);
        starOff = ContextCompat.getDrawable(MovieDetailedActivity.this, android.R.drawable.star_big_off);
    }
    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailedActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

}
