package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.jaylak.Models.Rate;
import com.example.android.jaylak.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Tech-4 on 3/26/2018.
 */

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {

    private ArrayList<Rate> rateArrayList = new ArrayList<>();
    private Context context;

    public RateAdapter(ArrayList<Rate> rateArrayList, Context context) {
        this.rateArrayList = rateArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item, parent, false);

        return new RateAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Rate rate = rateArrayList.get(position);

//        holder.name.setText(rate.getName());
//        Picasso.get().load(rate.getName()).into(holder.image);
//        holder.food.setRating(rate.getFoodRate());
//        holder.service.setNumStars(rate.getServiceRate());
//        holder.quality.setNumStars(rate.getQualityRate());
//        holder.location.setNumStars(rate.getLocationRate());
    }

    @Override
    public int getItemCount() {
        return rateArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;
        public RatingBar food;
        public RatingBar service;
        public RatingBar quality;
        public RatingBar location;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.rater_name);
            image = itemView.findViewById(R.id.rater_image);
            food = itemView.findViewById(R.id.rate_food);
            service = itemView.findViewById(R.id.rate_service);
            quality = itemView.findViewById(R.id.rate_quality);
            location = itemView.findViewById(R.id.rate_location);
        }
    }
}