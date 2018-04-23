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
 * Created by Tech-4 on 4/18/2018.
 */

public class BrandRateAdapter extends RecyclerView.Adapter<BrandRateAdapter.ViewHolder> {

    private ArrayList<Rate> rateArrayList = new ArrayList<>();
    private Context context;

    public BrandRateAdapter(Context context, ArrayList<Rate> rateArrayList) {
        this.context = context;
        this.rateArrayList = rateArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Rate rate = rateArrayList.get(position);

        holder.rateName.setText(rate.getName());
        holder.foodRate.setRating(rate.getFoodRate());
        holder.serviceRate.setRating(rate.getServiceRate());
        holder.locationRate.setRating(rate.getLocationRate());
        holder.qualityRate.setRating(rate.getQualityRate());
        Picasso.get().load(rate.getImage()).into(holder.rateImage);
    }

    @Override
    public int getItemCount() {
        return rateArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView rateImage;
        public TextView rateName;
        public RatingBar foodRate;
        public RatingBar serviceRate;
        public RatingBar locationRate;
        public RatingBar qualityRate;


        public ViewHolder(View itemView) {
            super(itemView);

            rateImage = itemView.findViewById(R.id.rater_image);
            rateName = itemView.findViewById(R.id.rater_name);
            foodRate = itemView.findViewById(R.id.rate_food);
            serviceRate = itemView.findViewById(R.id.rate_service);
            locationRate = itemView.findViewById(R.id.rate_location);
            qualityRate = itemView.findViewById(R.id.rate_quality);
        }
    }
}