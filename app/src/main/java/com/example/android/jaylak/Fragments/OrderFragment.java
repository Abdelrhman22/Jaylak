package com.example.android.jaylak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.jaylak.Models.ProfileHistory;
import com.example.android.jaylak.Adapters.ProfileHistoryAdapter;
import com.example.android.jaylak.R;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    private ArrayList<ProfileHistory> profileHistoryArrayList = new ArrayList<>();
    private ProfileHistoryAdapter profileHistoryAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public OrderFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.profile_order_history_recyclerview);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        profileHistoryAdapter = new ProfileHistoryAdapter(getContext(), profileHistoryArrayList);
        recyclerView.setAdapter(profileHistoryAdapter);

        getData();

        profileHistoryAdapter.notifyDataSetChanged();

        return view;
    }


    private ArrayList getData() {

        ProfileHistory profileHistory;

        profileHistory = new ProfileHistory("HM", "Cafe", getResources().getDrawable(R.drawable.cafes));
        profileHistoryArrayList.add(profileHistory);


        profileHistory = new ProfileHistory("HM", "Shop", getResources().getDrawable(R.drawable.shopping));
        profileHistoryArrayList.add(profileHistory);


        profileHistory = new ProfileHistory("HM", "Restaurants", getResources().getDrawable(R.drawable.beauty));
        profileHistoryArrayList.add(profileHistory);


        profileHistory = new ProfileHistory("HM", "Club", getResources().getDrawable(R.drawable.carservice));
        profileHistoryArrayList.add(profileHistory);


        return profileHistoryArrayList;
    }

}