package com.example.android.jaylak.Adapters;

import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.jaylak.Models.Interest;
import com.example.android.jaylak.R;

import java.util.ArrayList;

/**
 * Created by Tech-4 on 2/12/2018.
 */

public class InterestsAdapter extends BaseAdapter {

    private Context c;
    private ArrayList<Interest> interest;

    public InterestsAdapter(Context c, ArrayList<Interest> interest) {
        this.c = c;
        this.interest = interest;
    }

    @Override
    public int getCount() {
        return interest.size();
    }

    @Override
    public Object getItem(int i) {
        return interest.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.interest_item, viewGroup, false);
        }

        final Interest interest = (Interest) this.getItem(i);

        ImageView img = (ImageView) view.findViewById(R.id.interest_image);
        CheckBox name = (CheckBox) view.findViewById(R.id.interest_name);

        //BIND
        img.setImageDrawable(interest.getImage());
        name.setText(interest.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, interest.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}