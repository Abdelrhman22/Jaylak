package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.jaylak.Models.ProfileHistory;
import com.example.android.jaylak.R;

import java.util.ArrayList;

/**
 * Created by Tech-4 on 2/21/2018.
 */

public class ProfileHistoryAdapter extends RecyclerView.Adapter<ProfileHistoryAdapter.ViewHolder> {

    private ArrayList<ProfileHistory> profileHistoryArrayList = new ArrayList<>();
    private Context context;

    public ProfileHistoryAdapter(Context context, ArrayList<ProfileHistory> profileHistoryArrayList) {
        this.profileHistoryArrayList = profileHistoryArrayList;
        this.context = context;
    }


    @Override
    public ProfileHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_history_list_item, parent, false);

        return new ProfileHistoryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProfileHistory profileHistory = profileHistoryArrayList.get(position);

        holder.name.setText(profileHistory.getName());
        holder.details.setText(profileHistory.getDetails());
        holder.image.setImageDrawable(profileHistory.getImage());
    }

    @Override
    public int getItemCount() {
        return profileHistoryArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;

        public TextView name;

        public TextView details;

        public ViewHolder(View itemView) {

            super(itemView);

            image = itemView.findViewById(R.id.profile_history_image);

            name = itemView.findViewById(R.id.profile_history_name);

            details = itemView.findViewById(R.id.profile_history_details);

        }

        @Override
        public void onClick(View view) {

        }
    }
}