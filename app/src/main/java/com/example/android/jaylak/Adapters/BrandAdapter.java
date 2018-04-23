package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.jaylak.Activities.StoreReservationActivity;
import com.example.android.jaylak.Models.Brand;
import com.example.android.jaylak.R;

import java.util.ArrayList;

/**
 * Created by Tech-4 on 2/14/2018.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> implements Filterable {


    private ArrayList<Brand> brandArrayList = new ArrayList<>();
    private ArrayList<Brand> brandSearchList = new ArrayList<>();

    private Context context;

    public BrandAdapter(Context context, ArrayList<Brand> brandArrayList) {

        this.context = context;
        this.brandArrayList = brandArrayList;
        this.brandSearchList = brandArrayList;
    }

    @Override
    public BrandAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Brand brand = brandSearchList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StoreReservationActivity.class);
                context.startActivity(intent);

            }
        });

        holder.name.setText(brand.getName());
        holder.type.setText(brand.getType());
        holder.image.setImageDrawable(brand.getImage());
        holder.rate.setRating(brand.getRate());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return brandSearchList.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    brandSearchList = brandArrayList;
                } else {

                    ArrayList<Brand> searchFilterList = new ArrayList<>();
                    for (Brand brand : brandArrayList) {

                        if (brand.getName().toLowerCase().contains(charString) || brand.getType().toLowerCase().contains(charString)) {

                            searchFilterList.add(brand);
                        }
                    }
                    brandSearchList = searchFilterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = brandSearchList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                brandSearchList = (ArrayList<Brand>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;

        public TextView name;

        public TextView type;

        public RatingBar rate;

        public ViewHolder(View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.iv_brand_nearby_image);

            name = itemView.findViewById(R.id.tv_brand_nearby_title);

            type = itemView.findViewById(R.id.tv_brand_nearby_type);

            rate = itemView.findViewById(R.id.rating_brands_nearby);
        }

        @Override
        public void onClick(View view) {

        }
    }
}