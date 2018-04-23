package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.jaylak.Activities.BrandsActivity;

import com.example.android.jaylak.Models.CategoryItem;
import com.example.android.jaylak.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tech-4 on 2/14/2018.
 */

public class CategoryAdapter extends ArrayAdapter {

    private Context context;
    private List<CategoryItem> categoryArrayList = new ArrayList<>();

    public CategoryAdapter(Context context, List<CategoryItem> categoryArrayList) {

        super(context, 0,categoryArrayList);
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @Override
    public int getCount() {
        return categoryArrayList.size();
    }

    @Override
    public CategoryItem getItem(int i) {
        return categoryArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.category_item, viewGroup, false);
        }

        final CategoryItem category = getItem(i);

        ImageView img =  view.findViewById(R.id.category_item_image);
        TextView title =  view.findViewById(R.id.category_item_title);
        TextView list =  view.findViewById(R.id.category_item_list);

        //BIND
        Picasso.get().load(category.getImage()).into(img);
        title.setText(category.getName());
        list.setText(category.getCount() + " Listing");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, category.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BrandsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

        return view;
    }
}