package com.example.android.jaylak.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.jaylak.Models.ProfileAction;
import com.example.android.jaylak.R;

import java.util.ArrayList;

/**
 * Created by Tech-4 on 2/21/2018.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private ArrayList<ProfileAction> profileActionList = new ArrayList<>();
    private Context context;

    public ProfileAdapter(Context context, ArrayList<ProfileAction> profileActionList) {

        this.profileActionList = profileActionList;
        this.context = context;
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_action_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProfileAction profileAction = profileActionList.get(position);

        holder.name.setText(profileAction.getName());
        holder.date.setText(profileAction.getDate());
        holder.profileImage.setImageDrawable(profileAction.getImage());
        holder.profileAction.setImageDrawable(profileAction.getActionImage());

    }

    @Override
    public int getItemCount() {
        return profileActionList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView profileImage;

        public ImageView profileAction;

        public TextView name;

        public TextView date;

        public ViewHolder(View itemView) {

            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image_action_page);

            name = itemView.findViewById(R.id.profile_name_action_page);

            date = itemView.findViewById(R.id.profile_date_action_page);

            profileAction = itemView.findViewById(R.id.profile_action_page_overview);
        }

        @Override
        public void onClick(View view) {

        }
    }
}