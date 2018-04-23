package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.jaylak.Models.BrandCategory;
import com.example.android.jaylak.R;
import com.example.android.jaylak.Utilities.svgloader.GlideApp;

import java.util.ArrayList;


/**
 * Created by Tech-4 on 3/19/2018.
 */

public class BrandCategoryAdapter extends RecyclerView.Adapter<BrandCategoryAdapter.ViewHolder> {

    private ArrayList<BrandCategory> brandCategoryArrayList = new ArrayList<>();
    private Context context;

    public BrandCategoryAdapter(ArrayList<BrandCategory> brandCategoryArrayList, Context context) {
        this.brandCategoryArrayList = brandCategoryArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_category_item, parent, false);
        return new BrandCategoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BrandCategory brandCategory = brandCategoryArrayList.get(position);

        holder.name.setText(brandCategory.getName());
        holder.description.setText(brandCategory.getDescription());
//        Picasso.get().load(brandCategory.getImage()).into(holder.image);

        GlideApp.with(context)
                .load(brandCategory.getImage())
                .placeholder(R.drawable.icon)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return brandCategoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView image;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_brand_category_name);
            description = itemView.findViewById(R.id.tv_brand_category_description);
            image = itemView.findViewById(R.id.iv_brand_category_image);

        }
    }
}