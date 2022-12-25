package com.example.movies10;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    static int i =0;
    public static final String ON_BIND_TAG = "onBind";
    private List<Review> reviewList = new ArrayList<>();

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.reviewText.setText(review.getReviewText());
        holder.author.setText(review.getAuthor());
        String reviewType = review.getReviewType();
        holder.linearLayoutContainer.setBackgroundColor(
                getReviewColor(
                        holder.itemView.getContext(),reviewType));
        i++;
        Log.d(ON_BIND_TAG, "onBindViewHolder: "+i);
    }

    @Override
    public int getItemCount() {return reviewList.size();}


    int getReviewColor(Context context, String reviewType){
        int colorResId;
        int color;
        if (reviewType.equals("Позитивный")){
            colorResId = android.R.color.holo_green_light;
            return color = ContextCompat.getColor(context, colorResId);
        }else if(reviewType.equals("Нейтральный")){
            colorResId =android.R.color.holo_orange_light;
            return color = ContextCompat.getColor(context, colorResId);
        }else {
            colorResId = android.R.color.holo_red_light;
            return color = ContextCompat.getColor(context, colorResId);
        }
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder{
        TextView author;
        TextView reviewText;
        LinearLayout linearLayoutContainer;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            reviewText = itemView.findViewById(R.id.reviewText);
            linearLayoutContainer = itemView.findViewById(R.id.linearLayoutContainer);
        }
    }


}
