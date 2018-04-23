package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.jaylak.Activities.ProfileActivity;
import com.example.android.jaylak.R;
import com.example.android.jaylak.Models.StoreProduct;

import java.util.ArrayList;

/**
 * Created by Tech-4 on 2/19/2018.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private ArrayList<StoreProduct> storeArrayList = new ArrayList<>();
    private Context context;

    public StoreAdapter(Context context, ArrayList<StoreProduct> storeArrayList) {
        this.storeArrayList = storeArrayList;
        this.context = context;
    }


    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);

        return new StoreAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StoreProduct storeProduct = storeArrayList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                context.startActivity(intent);

            }
        });

        holder.name.setText(storeProduct.getName());
        holder.type.setText(storeProduct.getType());
        holder.image.setImageDrawable(storeProduct.getImage());
        holder.quantity.setText(String.valueOf(storeProduct.getPrice()));
        holder.price.setText(String.valueOf(storeProduct.getPrice()));
    }


    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;

        public TextView name;

        public TextView type;

        public TextView quantity;

        public TextView price;

        public ViewHolder(View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.store_product_image);

            name = itemView.findViewById(R.id.tv_store_product_name);

            type = itemView.findViewById(R.id.tv_store_product_type);

            quantity = itemView.findViewById(R.id.tv_store_product_quantity);

            price = itemView.findViewById(R.id.store_producct_price);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
