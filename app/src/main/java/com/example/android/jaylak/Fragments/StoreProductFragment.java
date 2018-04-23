package com.example.android.jaylak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.jaylak.R;
import com.example.android.jaylak.Adapters.StoreAdapter;
import com.example.android.jaylak.Models.StoreProduct;

import java.util.ArrayList;

public class StoreProductFragment extends Fragment {

    private ArrayList<StoreProduct> brandArrayList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private StoreAdapter storeAdapter;

    public StoreProductFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_store_product, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.store_product_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        storeAdapter = new StoreAdapter(getContext(), getData());
        recyclerView.setAdapter(storeAdapter);

        getData();

        storeAdapter.notifyDataSetChanged();

        return view;
    }

    private ArrayList getData() {

        StoreProduct storeProduct;

        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);

        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        storeProduct = new StoreProduct("HM", "Cafe", 5, 100, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(storeProduct);


        return brandArrayList;
    }
}