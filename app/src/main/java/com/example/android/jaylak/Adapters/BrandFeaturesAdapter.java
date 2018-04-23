package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.example.android.jaylak.Models.BrandFeature;
import com.example.android.jaylak.R;
import com.example.android.jaylak.Utilities.svgloader.GlideApp;
import com.example.android.jaylak.Utilities.svgloader.SvgSoftwareLayerSetter;
import com.github.megatronking.svg.support.extend.SVGImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Tech-4 on 3/19/2018.
 */

public class BrandFeaturesAdapter extends RecyclerView.Adapter<BrandFeaturesAdapter.ViewHolder> {

    private ArrayList<BrandFeature> brandFeatureArrayList = new ArrayList<>();
    private Context context;
    private RequestBuilder<PictureDrawable> requestBuilder;


    public BrandFeaturesAdapter(ArrayList<BrandFeature> brandFeatureArrayList, Context context) {
        this.brandFeatureArrayList = brandFeatureArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_item, parent, false);

        return new BrandFeaturesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BrandFeature brandFeature = brandFeatureArrayList.get(position);

        requestBuilder = GlideApp.with(context)
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());
        requestBuilder.load(brandFeature.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return brandFeatureArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;

        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.tv_feature_image_item);

        }

        @Override
        public void onClick(View v) {

        }
    }
}