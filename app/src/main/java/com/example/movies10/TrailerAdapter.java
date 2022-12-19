package com.example.movies10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private List<Trailer> trailers = new ArrayList<>();

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        Trailer trailer =  trailers.get(position);
        holder.trailerName.setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailerViewHolder extends RecyclerView.ViewHolder{
        TextView trailerName;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerName = itemView.findViewById(R.id.trailerName);
        }
    }
}
