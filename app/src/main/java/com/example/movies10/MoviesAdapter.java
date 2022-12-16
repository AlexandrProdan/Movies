package com.example.movies10;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{

    private List<Movie> moviesList = new ArrayList<>();
    private  OnReachEndListener onReachEndListener;
    private OnMovieClickListener onMovieClickListener;

    static int i = 0;
    static int j = 0;

    public void setOnMovieClickListener(OnMovieClickListener onPosterTouchListener) {
        this.onMovieClickListener = onPosterTouchListener;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        i++;
        Log.d("Adapter", "onCreateViewHolder: " + i);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        j++;
        Log.d("Adapter", "onBindViewHolder: "+ j);

        Movie movie = moviesList.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);

        double rating = movie.getRating().getKp();
        int backgroundId = getBackgroundByRating(rating);
        Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewRating.setBackground(drawable);
        holder.textViewRating.setText(String.format("%.1f",rating));

        if ((position >= (moviesList.size()-10)) && (onReachEndListener != null)){
            onReachEndListener.onReachEnd();
        }

        holder.imageViewPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onMovieClickListener!= null){
                    onMovieClickListener.onMovieClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    int getBackgroundByRating(double rating){
        int background;
        if(rating > 7){
            return background = R.drawable.circle_green;
        }else if(rating > 5){
            return background = R.drawable.circle_yellow;
        }else{
            return background = R.drawable.circle_red;
        }
    }


    interface OnReachEndListener{ public void onReachEnd();}
    interface OnMovieClickListener {public void onMovieClick(Movie movie);}


    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.ImageViewPoster);
            textViewRating = itemView.findViewById(R.id.TextViewRating);
        }
    }


}
